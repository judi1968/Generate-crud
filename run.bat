@echo off
set "dossierSource=%~dp0\kombarika"
set "dossierDestination=C:\"
set "dossierExcecution=C:\GenerateCrud\kommbarika"
set "dossierActuel=%CD%"

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

echo -
echo -
echo -
echo -
echo -
echo -
echo -
@echo off
echo copie termine
cd /d %dossierExcecution%
echo compilation du programme de GenerateCrud
start mvn compile
echo -
echo -
@echo off

echo excec du programme de GenerateCrud
mvn exec:java
@echo off

echo Retour au dossier source
cd /d "D:\Informatique et Technologie\Coding\Generate-crud"
