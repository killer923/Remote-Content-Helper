Remote-Content-Helper
=====================
This provides an API for fetching remote content.

Currently Supports : 
=====================
	
	->HTTP
	

To use :
=====================

Directly adding jar:
============
You can download the jar file from http://killer.heliohost.org/maven/com/killer923/dataFetcher/Remote-Content-Helper/0.0.1-SNAPSHOT/Remote-Content-Helper-0.0.1-SNAPSHOT.jar
and add it to your build path.

Maven:
============
In pom.xml of your project, <br>
Add dependency

	<dependencies> 
		<dependency>
			<groupId>com.killer923.dataFetcher</groupId>
			<artifactId>Remote-Content-Helper</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
	</dependencies>

Add repository 

	<repositories>
		<repository>
			<releases>
				<enabled>true</enabled>
				<updatePolicy>always</updatePolicy>
				<checksumPolicy>fail</checksumPolicy>
			</releases>
			<id>com.killer923</id>
			<name>killer Maven Repository</name>
			<url>http://killer.heliohost.org/maven</url>
			<layout>default</layout>
		</repository>
	</repositories>
