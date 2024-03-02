#!/bin/bash

dossierSource="$(dirname "$0")/kombarika"
dossierDestination="/home/mano/Documents/"
dossierExcecution="/home/mano/Documents/GenerateCrud/kombarika"

echo "========================"
echo "\\ MANO ANDRIASAT \\"
echo "  ========================"


if [ -d "$dossierExcecution" ]; then
    echo "Le dossier \"GenerateCrud\" existe. Suppression en cours..."
    rm -rf "$dossierExcecution"
    echo "Suppression terminée."
else
    echo "Le dossier \"GenerateCrud\" n'existe pas dans $dossierDestination."
fi


mkdir $dossierDestination"GenerateCrud"
mkdir $dossierDestination"GenerateCrud/kombarika"

echo "copie du code dans le dossier $dossierExcecution"
cp -r "$dossierSource"/* "$dossierExcecution"

echo "copie termine"
cd "$dossierExcecution"
echo "compilation du programme de GenerateCrud"
mvn compile
read -p "Appuyez sur Entrée pour continuer..."

echo "execution du programme de GenerateCrud"
mvn exec:java
read -p "Appuyez sur Entrée pour continuer..."

dossierJavaGenerateFront="$dossierExcecution/result_FrontEnd"
dossierJavaGenerateBack="$dossierExcecution/result_BackEnd"
dossierJavaGenerateUtil="$dossierExcecution/springUtil"

dossierSpringBoot="$dossierExcecution/spring_boot_result"
dossierJavaSpringBoot="$dossierSpringBoot/src/main/java"

echo "$dossierJavaGenerateBack"
echo "$dossierJavaSpringBoot"

mkdir -p "$dossierJavaSpringBoot"
cp -r "$dossierJavaGenerateBack"/* "$dossierJavaSpringBoot"

cd "$dossierSpringBoot"
mvn spring-boot:run
echo "Retour au dossier source"
cd "$HOME/Documents/Generate-crud"
