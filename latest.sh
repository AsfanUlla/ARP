#!/bin/bash

#Bash Script to List and Identify Devices on a Network

echo "Devices on the network are:"

#Assigning IP's and Mac's to array's
mapfile -t iparray < <(arp -an | cut -d ' ' -f 2)
mapfile -t macarray < <(arp -an | cut -d ' ' -f 4)
mapfile -t cmarray < <(arp -an | cut -d ' ' -f 4 | sed 's/://g' | tr "[:lower:]" "[:upper:]" | cut -c 1-6)
mapfile -t vendor < <(for i in "${cmarray[@]}"; do grep $i macs.txt; done)
#Finding Vendors

#Print result
len=$(echo "${#iparray[@]}")
olen=$(echo $((len-1)))
i=0

for ((i=0;i<=$olen;i++))
do
echo "IP:${iparray[$i]} MAC:${macarray[$i]} Vendor:${vendor[$i]}"
done


