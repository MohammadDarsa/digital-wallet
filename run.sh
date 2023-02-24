export PATH=$PATH":/c/Program Files/PostgreSQL/15/bin"
read -r -p 'Enter postgres password: ' password
PGPASSWORD=${password} psql -h localhost -p 5432 -d postgres -U postgres -a -q -f ./sql/data.sql
PGPASSWORD="cola" psql -h localhost -p 5430 -d bank -U pepsi -a -q -f ./sql/data2.sql

./gradlew bootRun & ./gradlew bank:bootRun && fg

