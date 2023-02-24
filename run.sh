export PATH=$PATH":/c/Program Files/PostgreSQL/15/bin"
read -r -p 'Enter postgres password: ' password
PGPASSWORD=${password} psql -h localhost -p 5432 -d postgres -U postgres -a -q -f ./sql/data.sql

./gradlew bootRun & ./gradlew bank:bootRun && fg

