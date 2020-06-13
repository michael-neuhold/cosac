$mysqlDriver = Join-Path $Env:SWE4_HOME "lib/database"

if (-not (Test-Path $mysqlDriver)) {
  Write-Error "Error: MySql JDBC driver $mysqlDriver not found."
  Exit 1
}

java -p "$mysqlDriver;../bin" -m database.cosacdb/database.CosacApplication
