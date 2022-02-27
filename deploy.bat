ECHO off

heroku war:deploy target/FinalProject-1.0-SNAPSHOT.war --app damp-harbor-69671
heroku open --app damp-harbor-69671



ECHO .
ECHO if no errors appear DB was created
PAUSE