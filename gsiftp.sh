#!/bin/bash

/usr/bin/java -cp gridftp/target/gridftp-2.2.0-SNAPSHOT.jar:ssl-proxies/target/ssl-proxies-2.2.0-SNAPSHOT.jar:gss/target/gss-2.2.0-SNAPSHOT.jar:jsse/target/jsse-2.2.0-SNAPSHOT.jar:lib/* org.globus.ftp.examples.GSIFtp "$@"
