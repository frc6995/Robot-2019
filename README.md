# Robot-2019
# 2019 FRC Deep Space Robot Code - FRC Team 6995

This Repository is for FRC 6995, 2019 Robot using VS Code

This repository is the code for the 2019 robot of the First Robotics Challenge (FRC) team, 6995 NOMAD.
Official FRC Java programming documentation
https://wpilib.screenstepslive.com/s/currentCS/m/java

## Requirements for running this code
This repository is only supported for development on a 64-bit Windows 10 environment.
Development Environment

## Software Environment

    •	Programming Language: Java

    •	IDE: Visual Studio

    •	Version Control: Git and GitHub

    •	FRC Driver Station: FRC Update Suite from National Instruments


## Installation Instructions
### 1.	Installation of Java and Visual Studio Code

	a. Install Java/Visual Studio Code using the instructions found here:
	https://wpilib.screenstepslive.com/s/currentCS/m/java/l/1027503-installing-c-and-java-development-tools-for-frc 
	the following components will be installed by following the instructions above:

	    •	Visual Studio Code 
			The supported IDE for 2019 robot code development. The offline installer sets up a separate
			copy of VSCode for WPILib development, even if you already have VSCode on your machine. This 
			is done because some of the settings that make the WPILib setup work may break existing workflows 
			if you use VSCode for other projects.

	    •	C++ Compiler 
			The toolchains for building C++ code for the roboRIO

	    •	Gradle
			The specific version of Gradle used for building/deploying C++ or Java robot code
			
	    •	Java JDK/JRE
			A specific version of the Java JDK/JRE that is used to build Java robot code and to run any of 
			the Java based Tools (Dashboards, etc.). This exists side by side with any existing JDK installs 
			and does not overwrite the JAVA_HOME variable

	    •	WPILib Tools
			SmartDashboard, Shuffleboard, Robot Builder, Outline Viewer, Pathweaver

	    •	WPILib Dependencies
			OpenCV, etc.

	    •	VSCode Extensions
			WPILib extensions for robot code development in VSCode

### 2.	FRC Driver Station Software - 

Install the FRC Update Suite from National Instruments which will allow you to communicate with the roboRIO- This needs the team-specific license number (Serial Number) available in Samepage (see below). 

	a. Create a user account on www.ni.com if you don’t already have one. 

	b. Follow the instructions to install the software here: 
	https://wpilib.screenstepslive.com/s/currentCS/m/java/l/1027504-installing-the-frc-update-suite-all-languages 

	c. To extract the FRC Update Suite zip files you will need the decryption key from kick off 
	which is $Robots&in#SPACE!!

	d. To install and activate the NI Vision Acquisition Software you will need our 2019 Serial 
	Number which is located on Samepage under 'Software Instructions/Help'.

### 3.	Git – We use Git and GitHub for version control.

	a. Download Git for Windows from https://gitforwindows.org. Run the installer, and before clicking the 
	Finish button select the checkbox to Launch the BASH shell, then click the Finish button. When the BASH 
	shell window appears type the following: 

		1. cd ~

		2. mkdir git

		3. cd git

		4. git clone https://github.com/frc6995/Robot-2019.git  
		
		5. This should create ~/Git/2019-DEEP-SPACE/, which contains the latest master version of the 
		FRC 6995 DEEP SPACE source code.
    
	b. For more detailed instructions for setting up Git for the first time see 
	https://git-scm.com/book/en/v2/Getting-Started-First-Time-Git-Setup.
	
### 4. CTRE Phoenix Library/Tuner (for Talons)

	Download and install the latest version of the CTRE Phoenix Framework Installer (link below). As of 
	2/10/2019 it is 5.13.0.0. Be sure to install the Phoenix Tuner along with roboRIO-FRC-C++/Java (Phoenix
	Library).
	http://www.ctr-electronics.com/hro.html#product_tabs_technical_resources
	
	You can find directions on using the Phoenix Library and Tuner at 
	https://phoenix-documentation.readthedocs.io/en/latest/ch01_PhoeSoftRefManual.html
	
	Be sure to check their blog posts during build season for any updates
	https://phoenix-documentation.readthedocs.io/en/latest/blog/blogs.html
	
## 5. Installed FRC Radio Utility
	You will need a utility to program the FRC radio on the robot. This is only needed when we first install a new 
	radio or when we come home from competition. Everyone should have this utility available on their computer for 
	support purposes. https://wpilib.screenstepslive.com/s/4485/m/13503/l/144986-programming-your-radio-for-home-use
