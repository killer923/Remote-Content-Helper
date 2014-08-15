Remote-Content-Helper
=====================
This provides an API for fetching remote content.

Currently Supports : 
=====================
	HTTP
	
For adding in maven :
=====================
Add under <dependencies> 

	<dependency>
		<groupId>com.killer923.dataFetcher</groupId>
		<artifactId>Remote-Content-Helper</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>

Add under <repositories>


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