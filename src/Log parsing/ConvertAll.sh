#!/bin/bash
FILES=./seperatedPackets/*
for f in $FILES
do
  echo "Processing $f..."
  # take action on each file. $f store current file name
  ./ConvertFormat.sh $f
done
