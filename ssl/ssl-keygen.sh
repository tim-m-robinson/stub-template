#/bin/sh
keytool -genkey -alias stub \
 -storetype PKCS12 -keyalg RSA -keysize 2048 \
 -keystore keystore.p12 -validity 3650 \
 -dname "CN=*.svc, OU=OJAS, O=Atos, L=London, ST=London, C=GB" \
 -storepass password
