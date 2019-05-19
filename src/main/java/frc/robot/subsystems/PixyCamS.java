package frc.robot.subsystems;

import java.util.ArrayList;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

//Thanks team 558

public class PixyCamS extends Subsystem {

	private static final int[] distances = { 0, 113, 81, 62, 49, 41, 35, 30,
			28, 25, 22, 21 };
	public static final double PIXY_FOV = 75; //Changes with lens change
	public static final double IMAGE_WIDTH = 320.0;
	public static final double IMAGE_WIDTHY = 200.0;
	public static final double GEAR_WIDTH_FT = 1.166;
	public static final int BLOCK_SIZE = 14;
	private static final double DEGREES_PER_PIXEL = PIXY_FOV / IMAGE_WIDTH;
	public static int PIXY_ADDRESS = 0x54;
	private I2C port;
	private boolean inRange;
	private boolean inRangeY;
	private double offset;
	private double offsetY;


	private ArrayList<PixyBlock> pixyBlocks = new ArrayList<>();

	public PixyCamS(){
		try{
			port = new I2C(I2C.Port.kOnboard, PIXY_ADDRESS);
		} catch (Exception e){
			System.out.println("e : " + e.getLocalizedMessage());
		}
	}

	public void initDefaultCommand(){

	}

	public void setLastOffset(double offset){
		if (offset > 1){
			this.offset = offset;
			setInRange(true);
		}
	}
  
  	public void setLastOffsetY(double offsetY){
		if (offsetY > 1){
				this.offsetY = offsetY;
				setInRangeY(true);
			}
		}

	public double getLastOffset(){
		return offset;
  	}
  
  	public double getLastOffsetY(){
		return offsetY;
	}

	public void setInRange(boolean inRange){
		this.inRange = inRange;
  	}
  
  	public void setInRangeY(boolean inRangeY){
		this.inRangeY = inRangeY;
	}

	public double getDistance(double width, double targetCenter){
		int index = 0;
		int smallest = 1000;
		for (int i = 0; i < distances.length; i++){
			if (Math.abs(width - distances[i]) < smallest){
				index = i;
				smallest = Math.abs((int) (width - distances[i]));
			}
		}
		double distance = index;
		return index;
	}

	public ArrayList<PixyBlock> read(){
		pixyBlocks.clear();
		pixyBlocks = new ArrayList<>();
		byte[] bytes = new byte[64];
		port.read(0x54, 64, bytes);
		int index = 0;
		for (; index < bytes.length - 1; ++index){
			int b1 = bytes[index];
			if (b1 < 0)
				b1 += 256;

			int b2 = bytes[index + 1];
			if (b2 < 0)
				b2 += 256;

			if (b1 == 0x55 && b2 == 0xaa)
				break;
		}

		if (index == 63)
			return null;
		else if (index == 0)
			index += 2;
		for (int byteOffset = index; byteOffset < bytes.length - BLOCK_SIZE - 1;){
			// checking for sync block
			int b1 = bytes[byteOffset];
			if (b1 < 0)
				b1 += 256;

			int b2 = bytes[byteOffset + 1];
			if (b2 < 0)
				b2 += 256;

			if (b1 == 0x55 && b2 == 0xaa){
				// copy block into temp buffer
				byte[] temp = new byte[BLOCK_SIZE];
				StringBuilder sb = new StringBuilder("Data : ");
				for (int tempOffset = 0; tempOffset < BLOCK_SIZE; ++tempOffset){
					temp[tempOffset] = bytes[byteOffset + tempOffset];
					sb.append(temp[tempOffset] + ", ");
				}
		
				PixyBlock block = bytesToBlock(temp);
				
				//Added so blocks are only added if their signature is 1 to remove noise from signal
				if (block.signature == 1){
					pixyBlocks.add(block);
					byteOffset += BLOCK_SIZE - 1;
				} else
					++byteOffset;
			} else
				++byteOffset;
		}

		if (pixyBlocks != null && pixyBlocks.size() > 0){
			if (pixyBlocks.size() >= 2){
				PixyBlock leftBlock;
				PixyBlock rightBlock;
				if (pixyBlocks.get(0).centerX > pixyBlocks.get(1).centerX){
					leftBlock = pixyBlocks.get(1);
					rightBlock = pixyBlocks.get(0);
				} else{
					leftBlock = pixyBlocks.get(0);
					rightBlock = pixyBlocks.get(1);
				}
				double difference = (rightBlock.centerX + leftBlock.centerX) / 2;
				System.out.println("Center X : " + difference);
				setLastOffset(difference);
				double total = (rightBlock.centerX) - (leftBlock.centerX);
				getDistance(total, difference);
			}
			
			else{
				
				setLastOffset(160); //Keeps robot going straight if only one signal is picked up
				
					}
		} else{
			setLastOffset(160); //Keeps robot going straight if nothing is picked up
			setInRange(false);
		}
		return pixyBlocks;
	}

	public PixyBlock bytesToBlock(byte[] bytes){
		PixyBlock pixyBlock = new PixyBlock();
		pixyBlock.sync = bytesToInt(bytes[1], bytes[0]);
		pixyBlock.checksum = bytesToInt(bytes[3], bytes[2]);

		
		pixyBlock.signature = orBytes(bytes[5], bytes[4]);
		pixyBlock.centerX = ((((int) bytes[7] & 0xff) << 8) | ((int) bytes[6] & 0xff));
		pixyBlock.centerY = ((((int) bytes[9] & 0xff) << 8) | ((int) bytes[8] & 0xff));
		pixyBlock.width = ((((int) bytes[11] & 0xff) << 8) | ((int) bytes[10] & 0xff));
		pixyBlock.height = ((((int) bytes[13] & 0xff) << 8) | ((int) bytes[12] & 0xff));
		return pixyBlock;
	}

	public int orBytes(byte b1, byte b2){
		return (b1 & 0xff) | (b2 & 0xff);
	}

	public int bytesToInt(int b1, int b2){
		if (b1 < 0)
			b1 += 256;

		if (b2 < 0)
			b2 += 256;

		int intValue = b1 * 256;
		intValue += b2;
		return intValue;
	}

	public class PixyBlock{
		// 0, 1 0 sync (0xaa55)
		// 2, 3 1 checksum (sum of all 16-bit words 2-6)
		// 4, 5 2 signature number
		// 6, 7 3 x center of object
		// 8, 9 4 y center of object
		// 10, 11 5 width of object
		// 12, 13 6 height of object

		// read byte : 85 read byte : -86
		// read byte : 85 read byte : -86
		// read byte : 22 read byte : 1
		// read by
		// read byte : -128 read byte : 0
		// read byte : 118 read byte : 0
		// read byte : 22 read byte : 0

		public int sync;
		public int checksum;
		public int signature;
		public int centerX;
		public int centerY;
		public int width;
		public int height;
	}
}
