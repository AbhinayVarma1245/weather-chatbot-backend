@echo off
:: === SET THIS TO YOUR PROJECT FOLDER PATH ===
set FOLDER_PATH=C:\Users\Admin\Downloads\weather-chatbot-backend

cd /d "%FOLDER_PATH%"

:: Remove any existing .git repo if broken
rmdir /s /q .git

:: Initialize Git and push to GitHub
git init
git add .
git commit -m "Initial commit"

:: Set GitHub remote
git remote add origin https://github.com/AbhinayVarma1245/weather-chatbot-backend.git
git branch -M main
git push -u origin main

echo.
echo ✅ Done! Check your repo online: https://github.com/AbhinayVarma1245/weather-chatbot-backend
pause
