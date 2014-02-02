#!/bin/bash
for ((a=1; a <= 500 ; a++))
do
    python ./Triangle_Two.py >> game_output.txt
done