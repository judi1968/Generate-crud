@echo off
set "dossierSource=%~dp0\kombarika"
set "dossierDestination=C:\"
set "dossierExcecution=C:\GenerateCrud\kombarika"



echo ========================
echo \ BIENVENUE GENERER CODE \
echo   ========================
if exist "%dossierExcecution%" (
    echo Le dossier "GenerateCrud" existe. Suppression en cours...
    rmdir /s /q "%dossierExcecution%"
    echo Suppression terminée.
) else (
    echo Le dossier "GenerateCrud" n'existe pas dans %dossierDestination%.
)
echo copie du code dans le dossier %dossierExcecution%
xcopy /s /e /y /i "%dossierSource%" "%dossierExcecution%"



@echo off
echo copie termine
cd /d %dossierExcecution%
echo compilation du programme de GenerateCrud
start mvn compile
pause
set "dossierActuel=%CD%"

@echo off

echo execution du programme de GenerateCrud
start mvn exec:java
pause
set "dossierJavaGenerateFront=%dossierExcecution%\result_FrontEnd"
set "dossierJavaGenerateBack=%dossierExcecution%\result_BackEnd"

set "dossierSpringBoot=%dossierExcecution%\spring_boot_result"
set "dossierJavaSpringBoot=%dossierSpringBoot%\src\main\java"

echo %dossierJavaGenerateBack%
echo %dossierJavaSpringBoot%


@REM copie de back-end dans de dossier qu'il  fallait
xcopy /s /e /y /i "%dossierJavaGenerateBack%" "%dossierJavaSpringBoot%"
@echo off
cd /d dossierSpringBoot
start mvn spring-boot:run
echo Retour au dossier source
cd /d "D:\Informatique et Technologie\Coding\Generate-crud"



