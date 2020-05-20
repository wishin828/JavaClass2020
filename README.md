# JavaClass2020
環境建置
fmw_12.2.1.0.0_wls_quick_Disk1_1of1.zip(WebLogic)
netbeans-8.1-windows.exe
jdk-8u51-windows-x64.exe
 
Oracle WebLogic Server 12.2.1  Quick Installer
==========================================================================================

The Oracle WebLogic Server 12.2.1 Quick Installer is a lightweight installer that contains all the necessary artifacts to develop and test applications on Oracle WebLogic Server 12.2.1.  The Quick Installer does not have a user interface and is run directly from command line. The Quick Installer is supported on Windows, Linux and Mac OS X systems. Installations performed with the Quick Installer can be patched using standard Oracle Patching tool, OPatch.

An optional supplemental quick installer (fmw_12.2.1.0.0_wls_supplemental_quick.jar) is available as a separate download and contains additional, non essential components such as the sample set, the Http Pub-Sub server and L10N console help files.
This version of Oracle WebLogic Server and the Quick Installer require the use of JDK 1.8.  Ensure that you have the proper JDK version installed and ready for use before starting.

QUICKSTART

1. Setup JAVA_HOME for the target platform.

2. If running on Windows you will need to run as Administrator.

3. Run the Quick Installer jar file and specify values are desired to define where the installation will be performed.  Some examples of running the Quick Installer are:

   $ java -jar fmw_12.2.1.0.0_wls_quick.jar

   This will use the current working directory as the ORACLE_HOME in which to install WebLogic Server. You must make sure that the current working directory (ORACLE_HOME) does not contain an existing wls12210 directory.

   $ java -jar fmw_12.2.1.0.0_wls_quick.jar ORACLE_HOME=<location>

  This will use the specified ORACLE_HOME as the directory in which to install WebLogic Server.
  
4. To see the full set of options available to run the Quick Installer specify the -help command when running it:

    $ java -jar fmw_12.2.1.0.0_wls_quick.jar -help

CONFIGURE AN ORACLE WEBLOGIC SERVER DOMAIN

Once the Quick Install has completed and installed Oracle WebLogic Server, a domain will need to be manually created. A domain can be created directly from the command line or it can be created using the Configuration Wizard.

CREATING A DOMAIN WITH THE CONFIGURATION WIZARD

Launch the Configuration Wizard

Linux 	$ . <ORACLE_HOME>/oracle_common/common/bin/config.sh

Mac 	$ . <ORACLE_HOME>/oracle_common/common/bin/config.sh

Windows > <ORACLE_HOME>\oracle_common\common\bin\config.cmd

CREATING A DOMAIN FROM THE COMMAND LINE

1.	Setup WebLogic Server environment in the current shell.

Linux 	$ . <ORACLE_HOME>/wlserver/server/bin/setWLSEnv.sh

Mac 	$ . <ORACLE_HOME>/wlserver/server/bin/setWLSEnv.sh

Windows > <ORACLE_HOME>\wlserver\server\bin\setWLSEnv.cmd

2. Create a new directory to store the domain and start the server.

Linux  
	$ mkdir domain
	$ cd domain
	$ JAVA_HOME/bin/java weblogic.Server

Mac
	$ mkdir domain
	$ cd domain
	$ $JAVA_HOME/bin/java weblogic.Server

Windows
	>  mkdir domain
	>  cd domain
	> %JAVA_HOME%\bin\java.exe  weblogic.Server

Once the domain is created, you should shutdown the server and restart it with the scripts provided in the newly created domain.  This will start the associated Derby database instance that is provided to make development easier with the Java EE 7 default datasource definition and operate as a default Job Repository for the Batch API.

REMOVE INSTALLATION

Delete the ORACLE_HOME directory.  If you have created domains or extracted Supplemental Quick Install, those files will be removed as well. 

WHAT IS NOT INCLUDED IN THE QUICK INSTALLER

- Native JNI libraries for unsupported platforms.
- Samples, non-english console help (can be added by using the WLS supplemental Quick Install)
- Oracle Configuration Manager (OCM) is not included in the Quick installer
- SCA is not included in the Quick Installer

