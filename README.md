# Digital Cookbook

This is the Digital Cookbook system created by Group3 of Software Engineering II in University of Applied Sciences in Luebeck, Germany, 2018.<br>

COPYRIGHT © 2018  Group “Are You Hungry” (Hua Yichen, Kong Yu, Wang Jungang, Shan Jiaxiang)  SWE II Java Project at FachHochSchule Lübeck  <br>

Professor & Supervisor: Prof. Mrs. Lenka Hanesová & Mr. Malte Grebe<br>

If you had any questions or constructive suggestions regarding our projects, feel free to write us an e-mail : [Address](yichen.hua@stud.fh-luebeck.de)<br>

Project is also presented on [Github](https://github.com/easonHua97/DigitalCookbook)<br>

## Brief Introduction to our System
***
Digital Cookbook is a JavaSE-based system which can be run as a desktop application.
To use our system, the user must firstly register or login. After login into our system, the user can 
either create new recipes, edit or delete recipes that is owned by the user, add/remove recipes as favorite
or export the recipes as PDF file containing basic information of the recipe.<br><br>
All the functional requirements are tested by our team members as well as other colleagues, without bugs.

Here are some screen-shot of our system:

![LoginFrame](/design/LoginFrame.png)<br>
<center>LoginFrame.png</center><br>
![RegisterFrame](/design/RegisterFrame.png)<br>
<center>RegisterFrame.png</center><br>
![MainFrame](/design/MainFrame.png)<br>
<center>MainFrame.png</center><br>
![EditFrame](/design/EditFrame.png)<br>
<center>EditFrame.png</center><br>


## How to run it on your PC?
***
### Prerequisites about Running Environment: <br>
1. Operation System: Windows XP/7/8/10, Linux (e.g. CentOS 7), MacOS.<br><br>
2. Java Environment(JREs 5.0+): [Java 8.0](http://www.oracle.com/technetwork/cn/java/javase/downloads/jdk8-downloads-2133151-zhs.html) is recommended.<br><br>
3. Integrated Development Environment Tools: [Eclipse Oxygen](https://www.eclipse.org) or [IntelliJ IDEA](https://www.jetbrains.com/idea/)
With Git plugins.<br><br>
4. MySQL Database Server:  [MySQL Server Community 5.7](https://dev.mysql.com/downloads/mysql/5.7.html#downloads) Recommended.

### Download and run codes Procedure <br>
1. Fork our project codes from my [Github Repository](https://github.com/easonHua97/DigitalCookbook)<br><br>
2. After you fork our codes and open them successfully in your IDE, please add two external JARs (mysql-connector-java-5.1.42-bin.jar
 and itextpdf-5.1.1.jar) to your **project structure**.<br><br>
3. Make sure that you have your Mysql root account with the password "root", Or you have to 
change database connection code in **de.fhluebeck.group3.DAO.BaseDAO.java**, see as follows:<br><br>
```Java
	/**
	 * Basic attributes for database.
	 */
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/cookbook_group3?characterEncoding=utf-8&useSSL=false";
	private static final String USERNAME = "your username";
	private static final String PASSWORD = "your password";
```
<br><br>
4. Execute the SQL file in the folder **design** called data.sql in your database.<br><br>
5. The Entrance of our Digital Cookbook is at **de.fhluebeck.group3.entrance.CookbookApp.java**
find and run it as Java Application.<br><br>
6. When you see the correct Welcome Navigator, it means all codes are working well. Enjoy yourself
in our Digital Cookbook.<br>

## CopyRight Declearation
***

The MIT License (MIT)

Copyright (c) Group “Are You Hungry” (Hua Yichen, Kong Yu, Wang Jungang, Shan Jiaxiang)  SWE II Java Project at FachHochSchule Lübeck, 2018

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.



