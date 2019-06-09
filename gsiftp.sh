#!/bin/bash

#/usr/bin/java -cp examples/target/examples-2.2.0-SNAPSHOT.jar:ssl-proxies/target/ssl-proxies-2.2.0-SNAPSHOT.jar:gss/target/gss-2.2.0-SNAPSHOT.jar:lib/* org.globus.examples.GSIFTP "$@"

/usr/bin/java -cp examples/target/examples-2.2.0-SNAPSHOT.jar org.globus.examples.GSIFTP "$@"