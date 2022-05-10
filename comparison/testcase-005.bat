@echo off
@echo Running Testcase 005: large input.
@echo ==============================================
@echo The input is:
@echo Too large.
@echo ----------------------------------------------
cd bin

rem : Run the testcase with input direction
java -Xss515m Postfix < ..\testcases\tc-005.infix

rem : Compare the expected output
@echo ----------------------------------------------
@echo The output should be: 
@echo Too large.

cd ..
@echo ==============================================
pause
@echo on
