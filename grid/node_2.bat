:: Beginning of hub batch file
set SERVER_VERSION=3.8.1
set TASK_NAME=SeleniumServerNode3
set REGISTER_IP=localhost:4444
set CHROME_DRIVER=D:\JavaProjects\GmailTest\chromedriver\chromedriver.exe
set GECKO_DRIVER=D:\JavaProjects\GmailTest\geckodriver\geckodriver.exe
set NODE_CONFIG=se-node_2-config.json

java -Dwebdriver.ie.driver=%IE_DRIVER% -Dwebdriver.gecko.driver=%GECKO_DRIVER% -Dwebdriver.chrome.driver=%CHROME_DRIVER% -jar selenium-server-standalone-%SERVER_VERSION%.jar -role node -hub http://%REGISTER_IP%/grid/register -nodeConfig %NODE_CONFIG%
:: End of hub batch file
pause