set BASE_DIR=%~dp0
%BASE_DIR:~0,2%
cd %BASE_DIR%
@call maven_package.bat
pause
copy /Y target\ad-admin.war c:\
pause