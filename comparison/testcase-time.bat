@echo off

@echo Running Testcase 100: Length of 10
@echo ==============================================
cd bin
java -Xss515m Postfix < ..\testcases\tc-100.infix
cd ..
@echo ==============================================

@echo Running Testcase 101: Length of 100
@echo ==============================================
cd bin
java -Xss515m Postfix < ..\testcases\tc-101.infix
cd ..
@echo ==============================================

@echo Running Testcase 102: Length of 1000
@echo ==============================================
cd bin
java -Xss515m Postfix < ..\testcases\tc-102.infix
cd ..
@echo ==============================================

@echo Running Testcase 103: Length of 10000
@echo ==============================================
cd bin
java -Xss515m Postfix < ..\testcases\tc-103.infix
cd ..
@echo ==============================================

@echo Running Testcase 104: Length of 100000
@echo ==============================================
cd bin
java -Xss515m Postfix < ..\testcases\tc-104.infix
cd ..
@echo ==============================================

@echo Running Testcase 105: Length of 1000000
@echo ==============================================
cd bin
java -Xss515m Postfix < ..\testcases\tc-105.infix
cd ..
@echo ==============================================

@echo Running Testcase 106: Length of 10000000
@echo ==============================================
cd bin
java -Xss515m Postfix < ..\testcases\tc-106.infix
cd ..
@echo ==============================================

pause
@echo on
