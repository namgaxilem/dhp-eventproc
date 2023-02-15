#!/bin/sh

sed "s/{{REACT_APP_CATALOG_URL}}/$REACT_APP_CATALOG_URL/g" ./index.tpl.html \
    | sed "s/{{TESTTEST}}/$TESTTEST/g" \
    | sed "s/{{TEST}}/$TEST/g" > ./index.html