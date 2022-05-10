@echo off
cd bin
java -cp ../util/junit-4.13.2.jar;.;../util/hamcrest-core-1.3.jar org.junit.runner.JUnitCore PostfixTest
cd ..
pause
@echo on
