#!/bin/bash
FILES=./seperatedPackets/*

#!/bin/bash
function stripHeader {
    cat ${1} | sed -r '1s/........................//'
}

for f in $FILES
do
  echo "Processing $f..."
  # take action on each file. $f store current file name
  ./ConvertFormat.sh $f
  head -n -4 $f >tmp1 && mv tmp1 $f
  stripHeader $f >tmp2 && mv tmp2 $f
done

for f in $FILES
do
  echo "Processing $f..."
  cat <(echo "INSERT INTO packets(packetID, label, SideOneByteOne,  SideOneByteTwo, SideOneByteThree,
                CornerFourByteOne, CornerFourByteTwo, CornerFourByteThree,
                SideFourByteOne, SideFourByteTwo, SideFourByteThree,
                CornerThreeByteOne, CornerThreeByteTwo, CornerThreeByteThree,
                LogoByteOne, LogoByteTwo, LogoByteThree,
                SideThreeByteOne, SideThreeByteTwo, SideThreeByteThree,
                CornerTwoByteOne, CornerTwoByteTwo, CornerTwoByteThree,
                SideTwoByteOne, SideTwoByteTwo, SideTwoByteThree,
                CornerOneByteOne, CornerOneByteTwo, CornerOneByteThree, UNKNOWN) VALUES (NULL, "\"RAIN_RANDOM.SQL\","") $f > temp.sql

  sed '$s/..$//' temp.sql > temp2.sql
  cat temp2.sql <(echo ");") > $f.sql
done

cd ./seperatedPackets/
rm -f *[13579]

echo "job done"