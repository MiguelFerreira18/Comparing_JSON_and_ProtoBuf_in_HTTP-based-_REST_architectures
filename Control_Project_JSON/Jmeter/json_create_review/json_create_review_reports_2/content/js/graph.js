/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
$(document).ready(function() {

    $(".click-title").mouseenter( function(    e){
        e.preventDefault();
        this.style.cursor="pointer";
    });
    $(".click-title").mousedown( function(event){
        event.preventDefault();
    });

    // Ugly code while this script is shared among several pages
    try{
        refreshHitsPerSecond(true);
    } catch(e){}
    try{
        refreshResponseTimeOverTime(true);
    } catch(e){}
    try{
        refreshResponseTimePercentiles();
    } catch(e){}
});


var responseTimePercentilesInfos = {
        data: {"result": {"minY": 120.0, "minX": 0.0, "maxY": 1160.0, "series": [{"data": [[0.0, 120.0], [0.1, 121.0], [0.2, 122.0], [0.3, 122.0], [0.4, 123.0], [0.5, 123.0], [0.6, 123.0], [0.7, 123.0], [0.8, 124.0], [0.9, 124.0], [1.0, 124.0], [1.1, 124.0], [1.2, 124.0], [1.3, 124.0], [1.4, 124.0], [1.5, 124.0], [1.6, 124.0], [1.7, 125.0], [1.8, 125.0], [1.9, 125.0], [2.0, 125.0], [2.1, 125.0], [2.2, 125.0], [2.3, 125.0], [2.4, 125.0], [2.5, 126.0], [2.6, 126.0], [2.7, 126.0], [2.8, 126.0], [2.9, 126.0], [3.0, 126.0], [3.1, 126.0], [3.2, 126.0], [3.3, 126.0], [3.4, 127.0], [3.5, 127.0], [3.6, 127.0], [3.7, 127.0], [3.8, 127.0], [3.9, 127.0], [4.0, 127.0], [4.1, 127.0], [4.2, 128.0], [4.3, 128.0], [4.4, 128.0], [4.5, 128.0], [4.6, 128.0], [4.7, 128.0], [4.8, 128.0], [4.9, 128.0], [5.0, 128.0], [5.1, 128.0], [5.2, 128.0], [5.3, 128.0], [5.4, 129.0], [5.5, 129.0], [5.6, 129.0], [5.7, 129.0], [5.8, 129.0], [5.9, 129.0], [6.0, 129.0], [6.1, 129.0], [6.2, 129.0], [6.3, 129.0], [6.4, 130.0], [6.5, 130.0], [6.6, 130.0], [6.7, 130.0], [6.8, 130.0], [6.9, 130.0], [7.0, 130.0], [7.1, 130.0], [7.2, 130.0], [7.3, 130.0], [7.4, 130.0], [7.5, 130.0], [7.6, 130.0], [7.7, 131.0], [7.8, 131.0], [7.9, 131.0], [8.0, 131.0], [8.1, 131.0], [8.2, 131.0], [8.3, 131.0], [8.4, 131.0], [8.5, 131.0], [8.6, 131.0], [8.7, 131.0], [8.8, 132.0], [8.9, 132.0], [9.0, 132.0], [9.1, 132.0], [9.2, 132.0], [9.3, 132.0], [9.4, 132.0], [9.5, 132.0], [9.6, 132.0], [9.7, 133.0], [9.8, 133.0], [9.9, 133.0], [10.0, 133.0], [10.1, 133.0], [10.2, 133.0], [10.3, 133.0], [10.4, 133.0], [10.5, 133.0], [10.6, 133.0], [10.7, 134.0], [10.8, 134.0], [10.9, 134.0], [11.0, 134.0], [11.1, 134.0], [11.2, 134.0], [11.3, 134.0], [11.4, 134.0], [11.5, 135.0], [11.6, 135.0], [11.7, 135.0], [11.8, 135.0], [11.9, 135.0], [12.0, 135.0], [12.1, 135.0], [12.2, 135.0], [12.3, 136.0], [12.4, 136.0], [12.5, 136.0], [12.6, 136.0], [12.7, 136.0], [12.8, 136.0], [12.9, 136.0], [13.0, 137.0], [13.1, 137.0], [13.2, 137.0], [13.3, 137.0], [13.4, 137.0], [13.5, 137.0], [13.6, 137.0], [13.7, 138.0], [13.8, 138.0], [13.9, 138.0], [14.0, 138.0], [14.1, 139.0], [14.2, 139.0], [14.3, 139.0], [14.4, 139.0], [14.5, 139.0], [14.6, 139.0], [14.7, 140.0], [14.8, 140.0], [14.9, 140.0], [15.0, 140.0], [15.1, 140.0], [15.2, 141.0], [15.3, 141.0], [15.4, 141.0], [15.5, 141.0], [15.6, 141.0], [15.7, 142.0], [15.8, 142.0], [15.9, 142.0], [16.0, 142.0], [16.1, 143.0], [16.2, 143.0], [16.3, 143.0], [16.4, 143.0], [16.5, 144.0], [16.6, 144.0], [16.7, 144.0], [16.8, 144.0], [16.9, 145.0], [17.0, 145.0], [17.1, 146.0], [17.2, 146.0], [17.3, 147.0], [17.4, 147.0], [17.5, 148.0], [17.6, 148.0], [17.7, 149.0], [17.8, 149.0], [17.9, 150.0], [18.0, 150.0], [18.1, 150.0], [18.2, 151.0], [18.3, 151.0], [18.4, 152.0], [18.5, 153.0], [18.6, 154.0], [18.7, 154.0], [18.8, 155.0], [18.9, 155.0], [19.0, 156.0], [19.1, 157.0], [19.2, 158.0], [19.3, 160.0], [19.4, 160.0], [19.5, 162.0], [19.6, 163.0], [19.7, 163.0], [19.8, 164.0], [19.9, 165.0], [20.0, 166.0], [20.1, 167.0], [20.2, 167.0], [20.3, 168.0], [20.4, 168.0], [20.5, 169.0], [20.6, 169.0], [20.7, 170.0], [20.8, 170.0], [20.9, 171.0], [21.0, 172.0], [21.1, 172.0], [21.2, 172.0], [21.3, 173.0], [21.4, 173.0], [21.5, 174.0], [21.6, 175.0], [21.7, 175.0], [21.8, 175.0], [21.9, 175.0], [22.0, 176.0], [22.1, 176.0], [22.2, 176.0], [22.3, 177.0], [22.4, 177.0], [22.5, 177.0], [22.6, 177.0], [22.7, 177.0], [22.8, 178.0], [22.9, 178.0], [23.0, 178.0], [23.1, 179.0], [23.2, 179.0], [23.3, 180.0], [23.4, 180.0], [23.5, 180.0], [23.6, 181.0], [23.7, 181.0], [23.8, 181.0], [23.9, 182.0], [24.0, 182.0], [24.1, 183.0], [24.2, 184.0], [24.3, 185.0], [24.4, 185.0], [24.5, 186.0], [24.6, 187.0], [24.7, 187.0], [24.8, 188.0], [24.9, 188.0], [25.0, 189.0], [25.1, 189.0], [25.2, 190.0], [25.3, 190.0], [25.4, 191.0], [25.5, 191.0], [25.6, 192.0], [25.7, 192.0], [25.8, 193.0], [25.9, 193.0], [26.0, 193.0], [26.1, 194.0], [26.2, 194.0], [26.3, 194.0], [26.4, 194.0], [26.5, 195.0], [26.6, 195.0], [26.7, 195.0], [26.8, 195.0], [26.9, 195.0], [27.0, 195.0], [27.1, 196.0], [27.2, 196.0], [27.3, 196.0], [27.4, 196.0], [27.5, 196.0], [27.6, 196.0], [27.7, 197.0], [27.8, 197.0], [27.9, 197.0], [28.0, 197.0], [28.1, 197.0], [28.2, 197.0], [28.3, 197.0], [28.4, 197.0], [28.5, 197.0], [28.6, 198.0], [28.7, 198.0], [28.8, 198.0], [28.9, 198.0], [29.0, 198.0], [29.1, 198.0], [29.2, 198.0], [29.3, 198.0], [29.4, 198.0], [29.5, 199.0], [29.6, 199.0], [29.7, 199.0], [29.8, 199.0], [29.9, 199.0], [30.0, 199.0], [30.1, 199.0], [30.2, 199.0], [30.3, 199.0], [30.4, 199.0], [30.5, 199.0], [30.6, 199.0], [30.7, 199.0], [30.8, 199.0], [30.9, 199.0], [31.0, 199.0], [31.1, 200.0], [31.2, 200.0], [31.3, 200.0], [31.4, 200.0], [31.5, 200.0], [31.6, 200.0], [31.7, 200.0], [31.8, 200.0], [31.9, 200.0], [32.0, 200.0], [32.1, 200.0], [32.2, 200.0], [32.3, 200.0], [32.4, 200.0], [32.5, 200.0], [32.6, 200.0], [32.7, 200.0], [32.8, 200.0], [32.9, 200.0], [33.0, 200.0], [33.1, 200.0], [33.2, 200.0], [33.3, 200.0], [33.4, 201.0], [33.5, 201.0], [33.6, 201.0], [33.7, 201.0], [33.8, 201.0], [33.9, 201.0], [34.0, 201.0], [34.1, 201.0], [34.2, 201.0], [34.3, 201.0], [34.4, 201.0], [34.5, 201.0], [34.6, 201.0], [34.7, 201.0], [34.8, 201.0], [34.9, 201.0], [35.0, 201.0], [35.1, 201.0], [35.2, 201.0], [35.3, 201.0], [35.4, 201.0], [35.5, 201.0], [35.6, 201.0], [35.7, 201.0], [35.8, 201.0], [35.9, 201.0], [36.0, 201.0], [36.1, 201.0], [36.2, 201.0], [36.3, 201.0], [36.4, 201.0], [36.5, 201.0], [36.6, 201.0], [36.7, 201.0], [36.8, 201.0], [36.9, 202.0], [37.0, 202.0], [37.1, 202.0], [37.2, 202.0], [37.3, 202.0], [37.4, 202.0], [37.5, 202.0], [37.6, 202.0], [37.7, 202.0], [37.8, 202.0], [37.9, 202.0], [38.0, 202.0], [38.1, 202.0], [38.2, 202.0], [38.3, 202.0], [38.4, 202.0], [38.5, 202.0], [38.6, 202.0], [38.7, 202.0], [38.8, 202.0], [38.9, 202.0], [39.0, 202.0], [39.1, 202.0], [39.2, 202.0], [39.3, 202.0], [39.4, 202.0], [39.5, 202.0], [39.6, 202.0], [39.7, 202.0], [39.8, 202.0], [39.9, 202.0], [40.0, 202.0], [40.1, 202.0], [40.2, 202.0], [40.3, 202.0], [40.4, 202.0], [40.5, 202.0], [40.6, 202.0], [40.7, 202.0], [40.8, 202.0], [40.9, 202.0], [41.0, 202.0], [41.1, 203.0], [41.2, 203.0], [41.3, 203.0], [41.4, 203.0], [41.5, 203.0], [41.6, 203.0], [41.7, 203.0], [41.8, 203.0], [41.9, 203.0], [42.0, 203.0], [42.1, 203.0], [42.2, 203.0], [42.3, 203.0], [42.4, 203.0], [42.5, 203.0], [42.6, 203.0], [42.7, 203.0], [42.8, 203.0], [42.9, 203.0], [43.0, 203.0], [43.1, 203.0], [43.2, 203.0], [43.3, 203.0], [43.4, 203.0], [43.5, 203.0], [43.6, 203.0], [43.7, 203.0], [43.8, 203.0], [43.9, 203.0], [44.0, 203.0], [44.1, 203.0], [44.2, 203.0], [44.3, 203.0], [44.4, 203.0], [44.5, 203.0], [44.6, 203.0], [44.7, 203.0], [44.8, 203.0], [44.9, 203.0], [45.0, 203.0], [45.1, 203.0], [45.2, 203.0], [45.3, 203.0], [45.4, 203.0], [45.5, 203.0], [45.6, 203.0], [45.7, 203.0], [45.8, 203.0], [45.9, 203.0], [46.0, 203.0], [46.1, 203.0], [46.2, 203.0], [46.3, 203.0], [46.4, 203.0], [46.5, 203.0], [46.6, 203.0], [46.7, 203.0], [46.8, 204.0], [46.9, 204.0], [47.0, 204.0], [47.1, 204.0], [47.2, 204.0], [47.3, 204.0], [47.4, 204.0], [47.5, 204.0], [47.6, 204.0], [47.7, 204.0], [47.8, 204.0], [47.9, 204.0], [48.0, 204.0], [48.1, 204.0], [48.2, 204.0], [48.3, 204.0], [48.4, 204.0], [48.5, 204.0], [48.6, 204.0], [48.7, 204.0], [48.8, 204.0], [48.9, 204.0], [49.0, 204.0], [49.1, 204.0], [49.2, 204.0], [49.3, 204.0], [49.4, 204.0], [49.5, 204.0], [49.6, 204.0], [49.7, 204.0], [49.8, 204.0], [49.9, 204.0], [50.0, 204.0], [50.1, 204.0], [50.2, 204.0], [50.3, 204.0], [50.4, 204.0], [50.5, 204.0], [50.6, 204.0], [50.7, 204.0], [50.8, 204.0], [50.9, 204.0], [51.0, 204.0], [51.1, 204.0], [51.2, 204.0], [51.3, 204.0], [51.4, 204.0], [51.5, 204.0], [51.6, 204.0], [51.7, 204.0], [51.8, 204.0], [51.9, 204.0], [52.0, 204.0], [52.1, 204.0], [52.2, 204.0], [52.3, 204.0], [52.4, 204.0], [52.5, 204.0], [52.6, 204.0], [52.7, 204.0], [52.8, 204.0], [52.9, 204.0], [53.0, 204.0], [53.1, 204.0], [53.2, 204.0], [53.3, 205.0], [53.4, 205.0], [53.5, 205.0], [53.6, 205.0], [53.7, 205.0], [53.8, 205.0], [53.9, 205.0], [54.0, 205.0], [54.1, 205.0], [54.2, 205.0], [54.3, 205.0], [54.4, 205.0], [54.5, 205.0], [54.6, 205.0], [54.7, 205.0], [54.8, 205.0], [54.9, 205.0], [55.0, 205.0], [55.1, 205.0], [55.2, 205.0], [55.3, 205.0], [55.4, 205.0], [55.5, 205.0], [55.6, 205.0], [55.7, 205.0], [55.8, 205.0], [55.9, 205.0], [56.0, 205.0], [56.1, 205.0], [56.2, 205.0], [56.3, 205.0], [56.4, 205.0], [56.5, 205.0], [56.6, 205.0], [56.7, 205.0], [56.8, 205.0], [56.9, 205.0], [57.0, 205.0], [57.1, 205.0], [57.2, 205.0], [57.3, 205.0], [57.4, 205.0], [57.5, 205.0], [57.6, 205.0], [57.7, 205.0], [57.8, 205.0], [57.9, 205.0], [58.0, 205.0], [58.1, 205.0], [58.2, 205.0], [58.3, 205.0], [58.4, 205.0], [58.5, 205.0], [58.6, 205.0], [58.7, 205.0], [58.8, 205.0], [58.9, 205.0], [59.0, 205.0], [59.1, 205.0], [59.2, 205.0], [59.3, 205.0], [59.4, 205.0], [59.5, 205.0], [59.6, 205.0], [59.7, 205.0], [59.8, 205.0], [59.9, 206.0], [60.0, 206.0], [60.1, 206.0], [60.2, 206.0], [60.3, 206.0], [60.4, 206.0], [60.5, 206.0], [60.6, 206.0], [60.7, 206.0], [60.8, 206.0], [60.9, 206.0], [61.0, 206.0], [61.1, 206.0], [61.2, 206.0], [61.3, 206.0], [61.4, 206.0], [61.5, 206.0], [61.6, 206.0], [61.7, 206.0], [61.8, 206.0], [61.9, 206.0], [62.0, 206.0], [62.1, 206.0], [62.2, 206.0], [62.3, 206.0], [62.4, 206.0], [62.5, 206.0], [62.6, 206.0], [62.7, 206.0], [62.8, 206.0], [62.9, 206.0], [63.0, 206.0], [63.1, 206.0], [63.2, 206.0], [63.3, 206.0], [63.4, 206.0], [63.5, 206.0], [63.6, 206.0], [63.7, 206.0], [63.8, 206.0], [63.9, 206.0], [64.0, 206.0], [64.1, 206.0], [64.2, 206.0], [64.3, 206.0], [64.4, 206.0], [64.5, 206.0], [64.6, 206.0], [64.7, 206.0], [64.8, 206.0], [64.9, 207.0], [65.0, 207.0], [65.1, 207.0], [65.2, 207.0], [65.3, 207.0], [65.4, 207.0], [65.5, 207.0], [65.6, 207.0], [65.7, 207.0], [65.8, 207.0], [65.9, 207.0], [66.0, 207.0], [66.1, 207.0], [66.2, 207.0], [66.3, 207.0], [66.4, 207.0], [66.5, 207.0], [66.6, 207.0], [66.7, 207.0], [66.8, 207.0], [66.9, 207.0], [67.0, 207.0], [67.1, 207.0], [67.2, 207.0], [67.3, 207.0], [67.4, 207.0], [67.5, 207.0], [67.6, 207.0], [67.7, 207.0], [67.8, 207.0], [67.9, 207.0], [68.0, 207.0], [68.1, 207.0], [68.2, 207.0], [68.3, 207.0], [68.4, 208.0], [68.5, 208.0], [68.6, 208.0], [68.7, 208.0], [68.8, 208.0], [68.9, 208.0], [69.0, 208.0], [69.1, 208.0], [69.2, 208.0], [69.3, 208.0], [69.4, 208.0], [69.5, 208.0], [69.6, 208.0], [69.7, 208.0], [69.8, 208.0], [69.9, 208.0], [70.0, 208.0], [70.1, 208.0], [70.2, 208.0], [70.3, 208.0], [70.4, 208.0], [70.5, 208.0], [70.6, 208.0], [70.7, 208.0], [70.8, 208.0], [70.9, 208.0], [71.0, 209.0], [71.1, 209.0], [71.2, 209.0], [71.3, 209.0], [71.4, 209.0], [71.5, 209.0], [71.6, 209.0], [71.7, 209.0], [71.8, 209.0], [71.9, 209.0], [72.0, 209.0], [72.1, 209.0], [72.2, 209.0], [72.3, 209.0], [72.4, 210.0], [72.5, 210.0], [72.6, 210.0], [72.7, 210.0], [72.8, 210.0], [72.9, 210.0], [73.0, 210.0], [73.1, 210.0], [73.2, 210.0], [73.3, 210.0], [73.4, 210.0], [73.5, 210.0], [73.6, 210.0], [73.7, 211.0], [73.8, 211.0], [73.9, 211.0], [74.0, 211.0], [74.1, 211.0], [74.2, 211.0], [74.3, 211.0], [74.4, 211.0], [74.5, 212.0], [74.6, 212.0], [74.7, 212.0], [74.8, 212.0], [74.9, 212.0], [75.0, 212.0], [75.1, 213.0], [75.2, 213.0], [75.3, 213.0], [75.4, 213.0], [75.5, 213.0], [75.6, 213.0], [75.7, 214.0], [75.8, 214.0], [75.9, 214.0], [76.0, 214.0], [76.1, 215.0], [76.2, 215.0], [76.3, 215.0], [76.4, 216.0], [76.5, 217.0], [76.6, 217.0], [76.7, 218.0], [76.8, 218.0], [76.9, 219.0], [77.0, 219.0], [77.1, 220.0], [77.2, 221.0], [77.3, 222.0], [77.4, 222.0], [77.5, 223.0], [77.6, 224.0], [77.7, 226.0], [77.8, 227.0], [77.9, 228.0], [78.0, 229.0], [78.1, 229.0], [78.2, 231.0], [78.3, 233.0], [78.4, 234.0], [78.5, 236.0], [78.6, 237.0], [78.7, 238.0], [78.8, 240.0], [78.9, 243.0], [79.0, 244.0], [79.1, 247.0], [79.2, 248.0], [79.3, 252.0], [79.4, 253.0], [79.5, 256.0], [79.6, 258.0], [79.7, 261.0], [79.8, 262.0], [79.9, 263.0], [80.0, 265.0], [80.1, 269.0], [80.2, 272.0], [80.3, 274.0], [80.4, 276.0], [80.5, 277.0], [80.6, 277.0], [80.7, 279.0], [80.8, 281.0], [80.9, 282.0], [81.0, 283.0], [81.1, 284.0], [81.2, 286.0], [81.3, 287.0], [81.4, 289.0], [81.5, 291.0], [81.6, 292.0], [81.7, 293.0], [81.8, 294.0], [81.9, 294.0], [82.0, 295.0], [82.1, 296.0], [82.2, 297.0], [82.3, 298.0], [82.4, 299.0], [82.5, 299.0], [82.6, 300.0], [82.7, 300.0], [82.8, 300.0], [82.9, 301.0], [83.0, 301.0], [83.1, 301.0], [83.2, 302.0], [83.3, 302.0], [83.4, 302.0], [83.5, 302.0], [83.6, 302.0], [83.7, 302.0], [83.8, 303.0], [83.9, 303.0], [84.0, 303.0], [84.1, 303.0], [84.2, 304.0], [84.3, 304.0], [84.4, 304.0], [84.5, 304.0], [84.6, 304.0], [84.7, 304.0], [84.8, 304.0], [84.9, 304.0], [85.0, 305.0], [85.1, 305.0], [85.2, 305.0], [85.3, 305.0], [85.4, 305.0], [85.5, 305.0], [85.6, 305.0], [85.7, 305.0], [85.8, 305.0], [85.9, 305.0], [86.0, 305.0], [86.1, 305.0], [86.2, 305.0], [86.3, 306.0], [86.4, 306.0], [86.5, 306.0], [86.6, 306.0], [86.7, 306.0], [86.8, 306.0], [86.9, 306.0], [87.0, 306.0], [87.1, 306.0], [87.2, 306.0], [87.3, 306.0], [87.4, 306.0], [87.5, 306.0], [87.6, 306.0], [87.7, 307.0], [87.8, 307.0], [87.9, 307.0], [88.0, 307.0], [88.1, 307.0], [88.2, 307.0], [88.3, 307.0], [88.4, 307.0], [88.5, 307.0], [88.6, 307.0], [88.7, 307.0], [88.8, 307.0], [88.9, 307.0], [89.0, 307.0], [89.1, 307.0], [89.2, 308.0], [89.3, 308.0], [89.4, 308.0], [89.5, 308.0], [89.6, 308.0], [89.7, 308.0], [89.8, 308.0], [89.9, 308.0], [90.0, 308.0], [90.1, 308.0], [90.2, 308.0], [90.3, 308.0], [90.4, 308.0], [90.5, 308.0], [90.6, 308.0], [90.7, 309.0], [90.8, 309.0], [90.9, 309.0], [91.0, 309.0], [91.1, 309.0], [91.2, 309.0], [91.3, 309.0], [91.4, 309.0], [91.5, 309.0], [91.6, 309.0], [91.7, 309.0], [91.8, 310.0], [91.9, 310.0], [92.0, 310.0], [92.1, 310.0], [92.2, 310.0], [92.3, 310.0], [92.4, 310.0], [92.5, 311.0], [92.6, 311.0], [92.7, 311.0], [92.8, 311.0], [92.9, 311.0], [93.0, 311.0], [93.1, 311.0], [93.2, 311.0], [93.3, 312.0], [93.4, 312.0], [93.5, 312.0], [93.6, 312.0], [93.7, 312.0], [93.8, 313.0], [93.9, 313.0], [94.0, 313.0], [94.1, 314.0], [94.2, 314.0], [94.3, 315.0], [94.4, 316.0], [94.5, 316.0], [94.6, 317.0], [94.7, 318.0], [94.8, 319.0], [94.9, 320.0], [95.0, 321.0], [95.1, 324.0], [95.2, 325.0], [95.3, 327.0], [95.4, 330.0], [95.5, 340.0], [95.6, 347.0], [95.7, 357.0], [95.8, 363.0], [95.9, 375.0], [96.0, 385.0], [96.1, 391.0], [96.2, 393.0], [96.3, 398.0], [96.4, 399.0], [96.5, 403.0], [96.6, 404.0], [96.7, 405.0], [96.8, 407.0], [96.9, 408.0], [97.0, 408.0], [97.1, 409.0], [97.2, 410.0], [97.3, 410.0], [97.4, 411.0], [97.5, 412.0], [97.6, 414.0], [97.7, 416.0], [97.8, 419.0], [97.9, 420.0], [98.0, 425.0], [98.1, 428.0], [98.2, 437.0], [98.3, 445.0], [98.4, 457.0], [98.5, 465.0], [98.6, 482.0], [98.7, 491.0], [98.8, 505.0], [98.9, 517.0], [99.0, 525.0], [99.1, 532.0], [99.2, 543.0], [99.3, 573.0], [99.4, 617.0], [99.5, 625.0], [99.6, 639.0], [99.7, 691.0], [99.8, 730.0], [99.9, 824.0]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
        getOptions: function() {
            return {
                series: {
                    points: { show: false }
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentiles'
                },
                xaxis: {
                    tickDecimals: 1,
                    axisLabel: "Percentiles",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Percentile value in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : %x.2 percentile was %y ms"
                },
                selection: { mode: "xy" },
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentiles"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesPercentiles"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesPercentiles"), dataset, prepareOverviewOptions(options));
        }
};

/**
 * @param elementId Id of element where we display message
 */
function setEmptyGraph(elementId) {
    $(function() {
        $(elementId).text("No graph series with filter="+seriesFilter);
    });
}

// Response times percentiles
function refreshResponseTimePercentiles() {
    var infos = responseTimePercentilesInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimePercentiles");
        return;
    }
    if (isGraph($("#flotResponseTimesPercentiles"))){
        infos.createGraph();
    } else {
        var choiceContainer = $("#choicesResponseTimePercentiles");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesPercentiles", "#overviewResponseTimesPercentiles");
        $('#bodyResponseTimePercentiles .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimeDistributionInfos = {
        data: {"result": {"minY": 1.0, "minX": 100.0, "maxY": 2574.0, "series": [{"data": [[1100.0, 1.0], [300.0, 692.0], [600.0, 19.0], [700.0, 6.0], [400.0, 117.0], [100.0, 1555.0], [200.0, 2574.0], [800.0, 3.0], [900.0, 2.0], [500.0, 30.0], [1000.0, 1.0]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 1100.0, "title": "Response Time Distribution"}},
        getOptions: function() {
            var granularity = this.data.result.granularity;
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    barWidth: this.data.result.granularity
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " responses for " + label + " were between " + xval + " and " + (xval + granularity) + " ms";
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimeDistribution"), prepareData(data.result.series, $("#choicesResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshResponseTimeDistribution() {
    var infos = responseTimeDistributionInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeDistribution");
        return;
    }
    if (isGraph($("#flotResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var syntheticResponseTimeDistributionInfos = {
        data: {"result": {"minY": 62.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 4938.0, "series": [{"data": [[0.0, 4938.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 62.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 1.0, "title": "Synthetic Response Times Distribution"}},
        getOptions: function() {
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendSyntheticResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times ranges",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                    tickLength:0,
                    min:-0.5,
                    max:3.5
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    align: "center",
                    barWidth: 0.25,
                    fill:.75
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " " + label;
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            options.xaxis.ticks = data.result.ticks;
            $.plot($("#flotSyntheticResponseTimeDistribution"), prepareData(data.result.series, $("#choicesSyntheticResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshSyntheticResponseTimeDistribution() {
    var infos = syntheticResponseTimeDistributionInfos;
    prepareSeries(infos.data, true);
    if (isGraph($("#flotSyntheticResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerSyntheticResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var activeThreadsOverTimeInfos = {
        data: {"result": {"minY": 1.0, "minX": 1.7405877E12, "maxY": 1.0, "series": [{"data": [[1.74058794E12, 1.0], [1.74058824E12, 1.0], [1.7405883E12, 1.0], [1.7405886E12, 1.0], [1.74058818E12, 1.0], [1.74058848E12, 1.0], [1.74058788E12, 1.0], [1.74058854E12, 1.0], [1.74058776E12, 1.0], [1.74058842E12, 1.0], [1.74058872E12, 1.0], [1.74058878E12, 1.0], [1.74058782E12, 1.0], [1.74058812E12, 1.0], [1.74058866E12, 1.0], [1.7405877E12, 1.0], [1.740588E12, 1.0], [1.74058806E12, 1.0], [1.74058836E12, 1.0]], "isOverall": false, "label": "Thread Group", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.74058878E12, "title": "Active Threads Over Time"}},
        getOptions: function() {
            return {
                series: {
                    stack: true,
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 6,
                    show: true,
                    container: '#legendActiveThreadsOverTime'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                selection: {
                    mode: 'xy'
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : At %x there were %y active threads"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesActiveThreadsOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotActiveThreadsOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewActiveThreadsOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Active Threads Over Time
function refreshActiveThreadsOverTime(fixTimestamps) {
    var infos = activeThreadsOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 0);
    }
    if(isGraph($("#flotActiveThreadsOverTime"))) {
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesActiveThreadsOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotActiveThreadsOverTime", "#overviewActiveThreadsOverTime");
        $('#footerActiveThreadsOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var timeVsThreadsInfos = {
        data: {"result": {"minY": 217.62539999999984, "minX": 1.0, "maxY": 217.62539999999984, "series": [{"data": [[1.0, 217.62539999999984]], "isOverall": false, "label": "HTTP Request", "isController": false}, {"data": [[1.0, 217.62539999999984]], "isOverall": false, "label": "HTTP Request-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 1.0, "title": "Time VS Threads"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: { noColumns: 2,show: true, container: '#legendTimeVsThreads' },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s: At %x.2 active threads, Average response time was %y.2 ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesTimeVsThreads"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotTimesVsThreads"), dataset, options);
            // setup overview
            $.plot($("#overviewTimesVsThreads"), dataset, prepareOverviewOptions(options));
        }
};

// Time vs threads
function refreshTimeVsThreads(){
    var infos = timeVsThreadsInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTimeVsThreads");
        return;
    }
    if(isGraph($("#flotTimesVsThreads"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTimeVsThreads");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTimesVsThreads", "#overviewTimesVsThreads");
        $('#footerTimeVsThreads .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var bytesThroughputOverTimeInfos = {
        data : {"result": {"minY": 847.3333333333334, "minX": 1.7405877E12, "maxY": 6089.4, "series": [{"data": [[1.74058794E12, 3079.3333333333335], [1.74058824E12, 3162.0], [1.7405883E12, 3131.0], [1.7405886E12, 2686.6666666666665], [1.74058818E12, 2841.6666666666665], [1.74058848E12, 2903.6666666666665], [1.74058788E12, 2903.6666666666665], [1.74058854E12, 2841.6666666666665], [1.74058776E12, 2996.6666666666665], [1.74058842E12, 2821.0], [1.74058872E12, 2211.3333333333335], [1.74058878E12, 2108.0], [1.74058782E12, 3089.6666666666665], [1.74058812E12, 2821.0], [1.74058866E12, 2366.3333333333335], [1.7405877E12, 847.3333333333334], [1.740588E12, 2883.0], [1.74058806E12, 2903.6666666666665], [1.74058836E12, 3069.0]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.74058794E12, 5930.2], [1.74058824E12, 6089.4], [1.7405883E12, 6029.7], [1.7405886E12, 5174.0], [1.74058818E12, 5472.5], [1.74058848E12, 5591.9], [1.74058788E12, 5591.9], [1.74058854E12, 5472.5], [1.74058776E12, 5771.0], [1.74058842E12, 5432.7], [1.74058872E12, 4258.6], [1.74058878E12, 4059.6], [1.74058782E12, 5950.1], [1.74058812E12, 5432.7], [1.74058866E12, 4557.1], [1.7405877E12, 1631.8], [1.740588E12, 5552.1], [1.74058806E12, 5591.9], [1.74058836E12, 5910.3]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.74058878E12, "title": "Bytes Throughput Over Time"}},
        getOptions : function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity) ,
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Bytes / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendBytesThroughputOverTime'
                },
                selection: {
                    mode: "xy"
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y"
                }
            };
        },
        createGraph : function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesBytesThroughputOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotBytesThroughputOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewBytesThroughputOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Bytes throughput Over Time
function refreshBytesThroughputOverTime(fixTimestamps) {
    var infos = bytesThroughputOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 0);
    }
    if(isGraph($("#flotBytesThroughputOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesBytesThroughputOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotBytesThroughputOverTime", "#overviewBytesThroughputOverTime");
        $('#footerBytesThroughputOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimesOverTimeInfos = {
        data: {"result": {"minY": 192.4878048780487, "minX": 1.7405877E12, "maxY": 278.9672897196259, "series": [{"data": [[1.74058794E12, 200.36912751677846], [1.74058824E12, 195.3725490196078], [1.7405883E12, 197.89108910891088], [1.7405886E12, 230.82307692307688], [1.74058818E12, 217.31636363636363], [1.74058848E12, 213.1103202846974], [1.74058788E12, 213.28113879003544], [1.74058854E12, 217.76000000000013], [1.74058776E12, 205.7896551724139], [1.74058842E12, 218.970695970696], [1.74058872E12, 278.9672897196259], [1.74058878E12, 270.1274509803922], [1.74058782E12, 199.97324414715715], [1.74058812E12, 219.61172161172166], [1.74058866E12, 261.1135371179038], [1.7405877E12, 192.4878048780487], [1.740588E12, 214.57347670250905], [1.74058806E12, 213.10320284697502], [1.74058836E12, 201.54882154882154]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.74058878E12, "title": "Response Time Over Time"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average response time was %y ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Times Over Time
function refreshResponseTimeOverTime(fixTimestamps) {
    var infos = responseTimesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 0);
    }
    if(isGraph($("#flotResponseTimesOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesOverTime", "#overviewResponseTimesOverTime");
        $('#footerResponseTimesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var latenciesOverTimeInfos = {
        data: {"result": {"minY": 192.19512195121956, "minX": 1.7405877E12, "maxY": 278.94392523364456, "series": [{"data": [[1.74058794E12, 200.32885906040258], [1.74058824E12, 195.343137254902], [1.7405883E12, 197.84818481848188], [1.7405886E12, 230.79230769230767], [1.74058818E12, 217.30181818181822], [1.74058848E12, 213.0391459074733], [1.74058788E12, 213.23843416370104], [1.74058854E12, 217.72727272727275], [1.74058776E12, 205.68620689655162], [1.74058842E12, 218.91208791208805], [1.74058872E12, 278.94392523364456], [1.74058878E12, 270.08823529411774], [1.74058782E12, 199.89632107023402], [1.74058812E12, 219.58974358974368], [1.74058866E12, 261.08733624454146], [1.7405877E12, 192.19512195121956], [1.740588E12, 214.5268817204301], [1.74058806E12, 213.0711743772242], [1.74058836E12, 201.49831649831657]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.74058878E12, "title": "Latencies Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response latencies in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendLatenciesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average latency was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesLatenciesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotLatenciesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewLatenciesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Latencies Over Time
function refreshLatenciesOverTime(fixTimestamps) {
    var infos = latenciesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyLatenciesOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 0);
    }
    if(isGraph($("#flotLatenciesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesLatenciesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotLatenciesOverTime", "#overviewLatenciesOverTime");
        $('#footerLatenciesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var connectTimeOverTimeInfos = {
        data: {"result": {"minY": 0.5327102803738317, "minX": 1.7405877E12, "maxY": 1.6707317073170733, "series": [{"data": [[1.74058794E12, 0.6610738255033555], [1.74058824E12, 0.6372549019607847], [1.7405883E12, 0.63036303630363], [1.7405886E12, 0.6615384615384617], [1.74058818E12, 0.5890909090909096], [1.74058848E12, 0.5907473309608539], [1.74058788E12, 0.9252669039145911], [1.74058854E12, 0.6109090909090906], [1.74058776E12, 1.0655172413793097], [1.74058842E12, 0.6703296703296705], [1.74058872E12, 0.5327102803738317], [1.74058878E12, 0.5931372549019606], [1.74058782E12, 1.0133779264214042], [1.74058812E12, 0.5897435897435894], [1.74058866E12, 0.5895196506550218], [1.7405877E12, 1.6707317073170733], [1.740588E12, 0.6989247311827957], [1.74058806E12, 0.637010676156584], [1.74058836E12, 0.5959595959595961]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.74058878E12, "title": "Connect Time Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getConnectTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average Connect Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendConnectTimeOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average connect time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesConnectTimeOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotConnectTimeOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewConnectTimeOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Connect Time Over Time
function refreshConnectTimeOverTime(fixTimestamps) {
    var infos = connectTimeOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyConnectTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 0);
    }
    if(isGraph($("#flotConnectTimeOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesConnectTimeOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotConnectTimeOverTime", "#overviewConnectTimeOverTime");
        $('#footerConnectTimeOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var responseTimePercentilesOverTimeInfos = {
        data: {"result": {"minY": 120.0, "minX": 1.7405877E12, "maxY": 1160.0, "series": [{"data": [[1.74058794E12, 677.0], [1.74058824E12, 691.0], [1.7405883E12, 782.0], [1.7405886E12, 497.0], [1.74058818E12, 528.0], [1.74058848E12, 456.0], [1.74058788E12, 563.0], [1.74058854E12, 441.0], [1.74058776E12, 504.0], [1.74058842E12, 444.0], [1.74058872E12, 824.0], [1.74058878E12, 901.0], [1.74058782E12, 1160.0], [1.74058812E12, 572.0], [1.74058866E12, 1033.0], [1.7405877E12, 920.0], [1.740588E12, 596.0], [1.74058806E12, 420.0], [1.74058836E12, 817.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.74058794E12, 306.0], [1.74058824E12, 311.0], [1.7405883E12, 307.0], [1.7405886E12, 309.0], [1.74058818E12, 307.0], [1.74058848E12, 305.0], [1.74058788E12, 305.0], [1.74058854E12, 306.0], [1.74058776E12, 304.90000000000003], [1.74058842E12, 308.0], [1.74058872E12, 419.5], [1.74058878E12, 415.0], [1.74058782E12, 299.0], [1.74058812E12, 308.0], [1.74058866E12, 399.0], [1.7405877E12, 275.6000000000001], [1.740588E12, 307.0], [1.74058806E12, 308.0], [1.74058836E12, 305.2]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.74058794E12, 540.2699999999988], [1.74058824E12, 533.7400000000001], [1.7405883E12, 662.9199999999973], [1.7405886E12, 479.2399999999998], [1.74058818E12, 452.5600000000004], [1.74058848E12, 412.7000000000001], [1.74058788E12, 428.60000000000014], [1.74058854E12, 410.0], [1.74058776E12, 435.5899999999987], [1.74058842E12, 411.26], [1.74058872E12, 799.7999999999994], [1.74058878E12, 729.75], [1.74058782E12, 576.0], [1.74058812E12, 534.9999999999995], [1.74058866E12, 625.0], [1.7405877E12, 920.0], [1.740588E12, 416.19999999999993], [1.74058806E12, 411.18], [1.74058836E12, 412.4399999999978]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.74058794E12, 311.0], [1.74058824E12, 319.2499999999999], [1.7405883E12, 315.20000000000005], [1.7405886E12, 316.9], [1.74058818E12, 312.0], [1.74058848E12, 307.9], [1.74058788E12, 309.0], [1.74058854E12, 309.0], [1.74058776E12, 314.34999999999997], [1.74058842E12, 309.0], [1.74058872E12, 527.5], [1.74058878E12, 522.75], [1.74058782E12, 308.0], [1.74058812E12, 310.0], [1.74058866E12, 463.5], [1.7405877E12, 510.0499999999997], [1.740588E12, 312.0], [1.74058806E12, 311.0], [1.74058836E12, 309.0]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.74058794E12, 121.0], [1.74058824E12, 121.0], [1.7405883E12, 120.0], [1.7405886E12, 126.0], [1.74058818E12, 123.0], [1.74058848E12, 124.0], [1.74058788E12, 126.0], [1.74058854E12, 124.0], [1.74058776E12, 122.0], [1.74058842E12, 124.0], [1.74058872E12, 137.0], [1.74058878E12, 140.0], [1.74058782E12, 126.0], [1.74058812E12, 122.0], [1.74058866E12, 134.0], [1.7405877E12, 122.0], [1.740588E12, 120.0], [1.74058806E12, 120.0], [1.74058836E12, 124.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.74058794E12, 203.0], [1.74058824E12, 194.0], [1.7405883E12, 199.0], [1.7405886E12, 206.0], [1.74058818E12, 204.0], [1.74058848E12, 204.0], [1.74058788E12, 204.0], [1.74058854E12, 205.0], [1.74058776E12, 202.5], [1.74058842E12, 205.0], [1.74058872E12, 218.5], [1.74058878E12, 216.5], [1.74058782E12, 201.0], [1.74058812E12, 204.0], [1.74058866E12, 215.0], [1.7405877E12, 139.0], [1.740588E12, 204.0], [1.74058806E12, 205.0], [1.74058836E12, 203.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.74058878E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Response Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentilesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Response time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentilesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimePercentilesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimePercentilesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Time Percentiles Over Time
function refreshResponseTimePercentilesOverTime(fixTimestamps) {
    var infos = responseTimePercentilesOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 0);
    }
    if(isGraph($("#flotResponseTimePercentilesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimePercentilesOverTime", "#overviewResponseTimePercentilesOverTime");
        $('#footerResponseTimePercentilesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var responseTimeVsRequestInfos = {
    data: {"result": {"minY": 130.0, "minX": 1.0, "maxY": 895.0, "series": [{"data": [[4.0, 206.0], [2.0, 403.5], [8.0, 130.0], [1.0, 895.0], [5.0, 204.0], [3.0, 298.5], [6.0, 182.0], [7.0, 136.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 8.0, "title": "Response Time Vs Request"}},
    getOptions: function() {
        return {
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Response Time in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: {
                noColumns: 2,
                show: true,
                container: '#legendResponseTimeVsRequest'
            },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median response time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesResponseTimeVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotResponseTimeVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewResponseTimeVsRequest"), dataset, prepareOverviewOptions(options));

    }
};

// Response Time vs Request
function refreshResponseTimeVsRequest() {
    var infos = responseTimeVsRequestInfos;
    prepareSeries(infos.data);
    if (isGraph($("#flotResponseTimeVsRequest"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeVsRequest");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimeVsRequest", "#overviewResponseTimeVsRequest");
        $('#footerResponseRimeVsRequest .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var latenciesVsRequestInfos = {
    data: {"result": {"minY": 130.0, "minX": 1.0, "maxY": 894.5, "series": [{"data": [[4.0, 206.0], [2.0, 403.0], [8.0, 130.0], [1.0, 894.5], [5.0, 204.0], [3.0, 298.5], [6.0, 182.0], [7.0, 135.5]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 8.0, "title": "Latencies Vs Request"}},
    getOptions: function() {
        return{
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Latency in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: { noColumns: 2,show: true, container: '#legendLatencyVsRequest' },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median Latency time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesLatencyVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotLatenciesVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewLatenciesVsRequest"), dataset, prepareOverviewOptions(options));
    }
};

// Latencies vs Request
function refreshLatenciesVsRequest() {
        var infos = latenciesVsRequestInfos;
        prepareSeries(infos.data);
        if(isGraph($("#flotLatenciesVsRequest"))){
            infos.createGraph();
        }else{
            var choiceContainer = $("#choicesLatencyVsRequest");
            createLegend(choiceContainer, infos);
            infos.createGraph();
            setGraphZoomable("#flotLatenciesVsRequest", "#overviewLatenciesVsRequest");
            $('#footerLatenciesVsRequest .legendColorBox > div').each(function(i){
                $(this).clone().prependTo(choiceContainer.find("li").eq(i));
            });
        }
};

var hitsPerSecondInfos = {
        data: {"result": {"minY": 1.3833333333333333, "minX": 1.7405877E12, "maxY": 5.1, "series": [{"data": [[1.74058794E12, 4.966666666666667], [1.74058824E12, 5.1], [1.7405883E12, 5.05], [1.7405886E12, 4.333333333333333], [1.74058818E12, 4.583333333333333], [1.74058848E12, 4.683333333333334], [1.74058788E12, 4.683333333333334], [1.74058854E12, 4.583333333333333], [1.74058776E12, 4.833333333333333], [1.74058842E12, 4.55], [1.74058872E12, 3.566666666666667], [1.74058878E12, 3.3833333333333333], [1.74058782E12, 4.983333333333333], [1.74058812E12, 4.55], [1.74058866E12, 3.816666666666667], [1.7405877E12, 1.3833333333333333], [1.740588E12, 4.65], [1.74058806E12, 4.683333333333334], [1.74058836E12, 4.95]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.74058878E12, "title": "Hits Per Second"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of hits / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendHitsPerSecond"
                },
                selection: {
                    mode : 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y.2 hits/sec"
                }
            };
        },
        createGraph: function createGraph() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesHitsPerSecond"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotHitsPerSecond"), dataset, options);
            // setup overview
            $.plot($("#overviewHitsPerSecond"), dataset, prepareOverviewOptions(options));
        }
};

// Hits per second
function refreshHitsPerSecond(fixTimestamps) {
    var infos = hitsPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 0);
    }
    if (isGraph($("#flotHitsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesHitsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotHitsPerSecond", "#overviewHitsPerSecond");
        $('#footerHitsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var codesPerSecondInfos = {
        data: {"result": {"minY": 1.3666666666666667, "minX": 1.7405877E12, "maxY": 5.1, "series": [{"data": [[1.74058794E12, 4.966666666666667], [1.74058824E12, 5.1], [1.7405883E12, 5.05], [1.7405886E12, 4.333333333333333], [1.74058818E12, 4.583333333333333], [1.74058848E12, 4.683333333333334], [1.74058788E12, 4.683333333333334], [1.74058854E12, 4.583333333333333], [1.74058776E12, 4.833333333333333], [1.74058842E12, 4.55], [1.74058872E12, 3.566666666666667], [1.74058878E12, 3.4], [1.74058782E12, 4.983333333333333], [1.74058812E12, 4.55], [1.74058866E12, 3.816666666666667], [1.7405877E12, 1.3666666666666667], [1.740588E12, 4.65], [1.74058806E12, 4.683333333333334], [1.74058836E12, 4.95]], "isOverall": false, "label": "201", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.74058878E12, "title": "Codes Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendCodesPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "Number of Response Codes %s at %x was %y.2 responses / sec"
                }
            };
        },
    createGraph: function() {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesCodesPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotCodesPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewCodesPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Codes per second
function refreshCodesPerSecond(fixTimestamps) {
    var infos = codesPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 0);
    }
    if(isGraph($("#flotCodesPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesCodesPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotCodesPerSecond", "#overviewCodesPerSecond");
        $('#footerCodesPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var transactionsPerSecondInfos = {
        data: {"result": {"minY": 1.3666666666666667, "minX": 1.7405877E12, "maxY": 5.1, "series": [{"data": [[1.74058794E12, 4.966666666666667], [1.74058824E12, 5.1], [1.7405883E12, 5.05], [1.7405886E12, 4.333333333333333], [1.74058818E12, 4.583333333333333], [1.74058848E12, 4.683333333333334], [1.74058788E12, 4.683333333333334], [1.74058854E12, 4.583333333333333], [1.74058776E12, 4.833333333333333], [1.74058842E12, 4.55], [1.74058872E12, 3.566666666666667], [1.74058878E12, 3.4], [1.74058782E12, 4.983333333333333], [1.74058812E12, 4.55], [1.74058866E12, 3.816666666666667], [1.7405877E12, 1.3666666666666667], [1.740588E12, 4.65], [1.74058806E12, 4.683333333333334], [1.74058836E12, 4.95]], "isOverall": false, "label": "HTTP Request-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.74058878E12, "title": "Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTransactionsPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                }
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTransactionsPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTransactionsPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewTransactionsPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Transactions per second
function refreshTransactionsPerSecond(fixTimestamps) {
    var infos = transactionsPerSecondInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTransactionsPerSecond");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 0);
    }
    if(isGraph($("#flotTransactionsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTransactionsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTransactionsPerSecond", "#overviewTransactionsPerSecond");
        $('#footerTransactionsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var totalTPSInfos = {
        data: {"result": {"minY": 1.3666666666666667, "minX": 1.7405877E12, "maxY": 5.1, "series": [{"data": [[1.74058794E12, 4.966666666666667], [1.74058824E12, 5.1], [1.7405883E12, 5.05], [1.7405886E12, 4.333333333333333], [1.74058818E12, 4.583333333333333], [1.74058848E12, 4.683333333333334], [1.74058788E12, 4.683333333333334], [1.74058854E12, 4.583333333333333], [1.74058776E12, 4.833333333333333], [1.74058842E12, 4.55], [1.74058872E12, 3.566666666666667], [1.74058878E12, 3.4], [1.74058782E12, 4.983333333333333], [1.74058812E12, 4.55], [1.74058866E12, 3.816666666666667], [1.7405877E12, 1.3666666666666667], [1.740588E12, 4.65], [1.74058806E12, 4.683333333333334], [1.74058836E12, 4.95]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.74058878E12, "title": "Total Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTotalTPS"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                },
                colors: ["#9ACD32", "#FF6347"]
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTotalTPS"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTotalTPS"), dataset, options);
        // setup overview
        $.plot($("#overviewTotalTPS"), dataset, prepareOverviewOptions(options));
    }
};

// Total Transactions per second
function refreshTotalTPS(fixTimestamps) {
    var infos = totalTPSInfos;
    // We want to ignore seriesFilter
    prepareSeries(infos.data, false, true);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 0);
    }
    if(isGraph($("#flotTotalTPS"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTotalTPS");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTotalTPS", "#overviewTotalTPS");
        $('#footerTotalTPS .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

// Collapse the graph matching the specified DOM element depending the collapsed
// status
function collapse(elem, collapsed){
    if(collapsed){
        $(elem).parent().find(".fa-chevron-up").removeClass("fa-chevron-up").addClass("fa-chevron-down");
    } else {
        $(elem).parent().find(".fa-chevron-down").removeClass("fa-chevron-down").addClass("fa-chevron-up");
        if (elem.id == "bodyBytesThroughputOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshBytesThroughputOverTime(true);
            }
            document.location.href="#bytesThroughputOverTime";
        } else if (elem.id == "bodyLatenciesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesOverTime(true);
            }
            document.location.href="#latenciesOverTime";
        } else if (elem.id == "bodyCustomGraph") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCustomGraph(true);
            }
            document.location.href="#responseCustomGraph";
        } else if (elem.id == "bodyConnectTimeOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshConnectTimeOverTime(true);
            }
            document.location.href="#connectTimeOverTime";
        } else if (elem.id == "bodyResponseTimePercentilesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimePercentilesOverTime(true);
            }
            document.location.href="#responseTimePercentilesOverTime";
        } else if (elem.id == "bodyResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeDistribution();
            }
            document.location.href="#responseTimeDistribution" ;
        } else if (elem.id == "bodySyntheticResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshSyntheticResponseTimeDistribution();
            }
            document.location.href="#syntheticResponseTimeDistribution" ;
        } else if (elem.id == "bodyActiveThreadsOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshActiveThreadsOverTime(true);
            }
            document.location.href="#activeThreadsOverTime";
        } else if (elem.id == "bodyTimeVsThreads") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTimeVsThreads();
            }
            document.location.href="#timeVsThreads" ;
        } else if (elem.id == "bodyCodesPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCodesPerSecond(true);
            }
            document.location.href="#codesPerSecond";
        } else if (elem.id == "bodyTransactionsPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTransactionsPerSecond(true);
            }
            document.location.href="#transactionsPerSecond";
        } else if (elem.id == "bodyTotalTPS") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTotalTPS(true);
            }
            document.location.href="#totalTPS";
        } else if (elem.id == "bodyResponseTimeVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeVsRequest();
            }
            document.location.href="#responseTimeVsRequest";
        } else if (elem.id == "bodyLatenciesVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesVsRequest();
            }
            document.location.href="#latencyVsRequest";
        }
    }
}

/*
 * Activates or deactivates all series of the specified graph (represented by id parameter)
 * depending on checked argument.
 */
function toggleAll(id, checked){
    var placeholder = document.getElementById(id);

    var cases = $(placeholder).find(':checkbox');
    cases.prop('checked', checked);
    $(cases).parent().children().children().toggleClass("legend-disabled", !checked);

    var choiceContainer;
    if ( id == "choicesBytesThroughputOverTime"){
        choiceContainer = $("#choicesBytesThroughputOverTime");
        refreshBytesThroughputOverTime(false);
    } else if(id == "choicesResponseTimesOverTime"){
        choiceContainer = $("#choicesResponseTimesOverTime");
        refreshResponseTimeOverTime(false);
    }else if(id == "choicesResponseCustomGraph"){
        choiceContainer = $("#choicesResponseCustomGraph");
        refreshCustomGraph(false);
    } else if ( id == "choicesLatenciesOverTime"){
        choiceContainer = $("#choicesLatenciesOverTime");
        refreshLatenciesOverTime(false);
    } else if ( id == "choicesConnectTimeOverTime"){
        choiceContainer = $("#choicesConnectTimeOverTime");
        refreshConnectTimeOverTime(false);
    } else if ( id == "choicesResponseTimePercentilesOverTime"){
        choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        refreshResponseTimePercentilesOverTime(false);
    } else if ( id == "choicesResponseTimePercentiles"){
        choiceContainer = $("#choicesResponseTimePercentiles");
        refreshResponseTimePercentiles();
    } else if(id == "choicesActiveThreadsOverTime"){
        choiceContainer = $("#choicesActiveThreadsOverTime");
        refreshActiveThreadsOverTime(false);
    } else if ( id == "choicesTimeVsThreads"){
        choiceContainer = $("#choicesTimeVsThreads");
        refreshTimeVsThreads();
    } else if ( id == "choicesSyntheticResponseTimeDistribution"){
        choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        refreshSyntheticResponseTimeDistribution();
    } else if ( id == "choicesResponseTimeDistribution"){
        choiceContainer = $("#choicesResponseTimeDistribution");
        refreshResponseTimeDistribution();
    } else if ( id == "choicesHitsPerSecond"){
        choiceContainer = $("#choicesHitsPerSecond");
        refreshHitsPerSecond(false);
    } else if(id == "choicesCodesPerSecond"){
        choiceContainer = $("#choicesCodesPerSecond");
        refreshCodesPerSecond(false);
    } else if ( id == "choicesTransactionsPerSecond"){
        choiceContainer = $("#choicesTransactionsPerSecond");
        refreshTransactionsPerSecond(false);
    } else if ( id == "choicesTotalTPS"){
        choiceContainer = $("#choicesTotalTPS");
        refreshTotalTPS(false);
    } else if ( id == "choicesResponseTimeVsRequest"){
        choiceContainer = $("#choicesResponseTimeVsRequest");
        refreshResponseTimeVsRequest();
    } else if ( id == "choicesLatencyVsRequest"){
        choiceContainer = $("#choicesLatencyVsRequest");
        refreshLatenciesVsRequest();
    }
    var color = checked ? "black" : "#818181";
    if(choiceContainer != null) {
        choiceContainer.find("label").each(function(){
            this.style.color = color;
        });
    }
}

