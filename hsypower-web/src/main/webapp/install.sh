#!/bin/bash

code_path=`pwd`
hsy_dynamic="/www/hsypower_dynamic/"
hsy_static="/www/hsypower_static/"

cd $hsy_dynamic
rm -rf jsp/ META-INF/ WEB-INF/
cd $code_path
mv jsp/ META-INF/ WEB-INF/ $hsy_dynamic

cd $hsy_static
rm -rf css/ favicon.ico images/ install.sh js/ sql/ widget/
cd $code_path
mv css/ images/ js/ sql/ widget/ favicon.ico install.sh robots.txt $hsy_static
