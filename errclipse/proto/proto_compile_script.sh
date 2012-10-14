#!/bin/bash

DST_DIR=$1
TARGET=$2
protoc --java_out=$DST_DIR $TARGET
