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
        data: {"result": {"minY": 123.0, "minX": 0.0, "maxY": 1331.0, "series": [{"data": [[0.0, 123.0], [0.1, 124.0], [0.2, 125.0], [0.3, 125.0], [0.4, 126.0], [0.5, 126.0], [0.6, 126.0], [0.7, 126.0], [0.8, 127.0], [0.9, 127.0], [1.0, 127.0], [1.1, 127.0], [1.2, 127.0], [1.3, 128.0], [1.4, 128.0], [1.5, 128.0], [1.6, 128.0], [1.7, 128.0], [1.8, 128.0], [1.9, 129.0], [2.0, 129.0], [2.1, 129.0], [2.2, 129.0], [2.3, 129.0], [2.4, 129.0], [2.5, 130.0], [2.6, 130.0], [2.7, 130.0], [2.8, 130.0], [2.9, 130.0], [3.0, 130.0], [3.1, 130.0], [3.2, 130.0], [3.3, 130.0], [3.4, 131.0], [3.5, 131.0], [3.6, 131.0], [3.7, 131.0], [3.8, 131.0], [3.9, 131.0], [4.0, 131.0], [4.1, 131.0], [4.2, 131.0], [4.3, 132.0], [4.4, 132.0], [4.5, 132.0], [4.6, 132.0], [4.7, 132.0], [4.8, 132.0], [4.9, 132.0], [5.0, 132.0], [5.1, 132.0], [5.2, 133.0], [5.3, 133.0], [5.4, 133.0], [5.5, 133.0], [5.6, 133.0], [5.7, 133.0], [5.8, 133.0], [5.9, 133.0], [6.0, 134.0], [6.1, 134.0], [6.2, 134.0], [6.3, 134.0], [6.4, 134.0], [6.5, 134.0], [6.6, 134.0], [6.7, 134.0], [6.8, 134.0], [6.9, 135.0], [7.0, 135.0], [7.1, 135.0], [7.2, 135.0], [7.3, 135.0], [7.4, 135.0], [7.5, 136.0], [7.6, 136.0], [7.7, 136.0], [7.8, 136.0], [7.9, 136.0], [8.0, 136.0], [8.1, 136.0], [8.2, 137.0], [8.3, 137.0], [8.4, 137.0], [8.5, 137.0], [8.6, 138.0], [8.7, 138.0], [8.8, 138.0], [8.9, 138.0], [9.0, 139.0], [9.1, 139.0], [9.2, 139.0], [9.3, 139.0], [9.4, 140.0], [9.5, 140.0], [9.6, 140.0], [9.7, 141.0], [9.8, 141.0], [9.9, 141.0], [10.0, 141.0], [10.1, 142.0], [10.2, 142.0], [10.3, 143.0], [10.4, 143.0], [10.5, 143.0], [10.6, 144.0], [10.7, 144.0], [10.8, 145.0], [10.9, 145.0], [11.0, 145.0], [11.1, 146.0], [11.2, 146.0], [11.3, 147.0], [11.4, 148.0], [11.5, 149.0], [11.6, 149.0], [11.7, 150.0], [11.8, 152.0], [11.9, 152.0], [12.0, 153.0], [12.1, 154.0], [12.2, 155.0], [12.3, 156.0], [12.4, 156.0], [12.5, 157.0], [12.6, 158.0], [12.7, 159.0], [12.8, 159.0], [12.9, 160.0], [13.0, 161.0], [13.1, 161.0], [13.2, 163.0], [13.3, 164.0], [13.4, 164.0], [13.5, 165.0], [13.6, 166.0], [13.7, 166.0], [13.8, 167.0], [13.9, 167.0], [14.0, 167.0], [14.1, 168.0], [14.2, 168.0], [14.3, 169.0], [14.4, 169.0], [14.5, 170.0], [14.6, 170.0], [14.7, 170.0], [14.8, 170.0], [14.9, 171.0], [15.0, 171.0], [15.1, 171.0], [15.2, 172.0], [15.3, 172.0], [15.4, 173.0], [15.5, 173.0], [15.6, 174.0], [15.7, 174.0], [15.8, 174.0], [15.9, 175.0], [16.0, 175.0], [16.1, 175.0], [16.2, 176.0], [16.3, 176.0], [16.4, 176.0], [16.5, 176.0], [16.6, 177.0], [16.7, 177.0], [16.8, 177.0], [16.9, 178.0], [17.0, 178.0], [17.1, 179.0], [17.2, 179.0], [17.3, 179.0], [17.4, 179.0], [17.5, 180.0], [17.6, 181.0], [17.7, 181.0], [17.8, 182.0], [17.9, 182.0], [18.0, 183.0], [18.1, 184.0], [18.2, 184.0], [18.3, 185.0], [18.4, 186.0], [18.5, 187.0], [18.6, 188.0], [18.7, 188.0], [18.8, 189.0], [18.9, 190.0], [19.0, 190.0], [19.1, 191.0], [19.2, 192.0], [19.3, 193.0], [19.4, 193.0], [19.5, 194.0], [19.6, 194.0], [19.7, 194.0], [19.8, 195.0], [19.9, 195.0], [20.0, 195.0], [20.1, 196.0], [20.2, 196.0], [20.3, 196.0], [20.4, 196.0], [20.5, 196.0], [20.6, 196.0], [20.7, 197.0], [20.8, 197.0], [20.9, 197.0], [21.0, 197.0], [21.1, 197.0], [21.2, 197.0], [21.3, 197.0], [21.4, 197.0], [21.5, 198.0], [21.6, 198.0], [21.7, 198.0], [21.8, 198.0], [21.9, 198.0], [22.0, 198.0], [22.1, 198.0], [22.2, 198.0], [22.3, 198.0], [22.4, 198.0], [22.5, 198.0], [22.6, 198.0], [22.7, 198.0], [22.8, 199.0], [22.9, 199.0], [23.0, 199.0], [23.1, 199.0], [23.2, 199.0], [23.3, 199.0], [23.4, 199.0], [23.5, 199.0], [23.6, 199.0], [23.7, 199.0], [23.8, 199.0], [23.9, 199.0], [24.0, 199.0], [24.1, 199.0], [24.2, 199.0], [24.3, 199.0], [24.4, 199.0], [24.5, 200.0], [24.6, 200.0], [24.7, 200.0], [24.8, 200.0], [24.9, 200.0], [25.0, 200.0], [25.1, 200.0], [25.2, 200.0], [25.3, 200.0], [25.4, 200.0], [25.5, 200.0], [25.6, 200.0], [25.7, 200.0], [25.8, 200.0], [25.9, 200.0], [26.0, 200.0], [26.1, 200.0], [26.2, 200.0], [26.3, 200.0], [26.4, 200.0], [26.5, 200.0], [26.6, 200.0], [26.7, 200.0], [26.8, 200.0], [26.9, 200.0], [27.0, 200.0], [27.1, 200.0], [27.2, 200.0], [27.3, 200.0], [27.4, 201.0], [27.5, 201.0], [27.6, 201.0], [27.7, 201.0], [27.8, 201.0], [27.9, 201.0], [28.0, 201.0], [28.1, 201.0], [28.2, 201.0], [28.3, 201.0], [28.4, 201.0], [28.5, 201.0], [28.6, 201.0], [28.7, 201.0], [28.8, 201.0], [28.9, 201.0], [29.0, 201.0], [29.1, 201.0], [29.2, 201.0], [29.3, 201.0], [29.4, 201.0], [29.5, 201.0], [29.6, 201.0], [29.7, 201.0], [29.8, 201.0], [29.9, 201.0], [30.0, 201.0], [30.1, 201.0], [30.2, 201.0], [30.3, 201.0], [30.4, 201.0], [30.5, 201.0], [30.6, 201.0], [30.7, 201.0], [30.8, 201.0], [30.9, 201.0], [31.0, 201.0], [31.1, 201.0], [31.2, 201.0], [31.3, 201.0], [31.4, 202.0], [31.5, 202.0], [31.6, 202.0], [31.7, 202.0], [31.8, 202.0], [31.9, 202.0], [32.0, 202.0], [32.1, 202.0], [32.2, 202.0], [32.3, 202.0], [32.4, 202.0], [32.5, 202.0], [32.6, 202.0], [32.7, 202.0], [32.8, 202.0], [32.9, 202.0], [33.0, 202.0], [33.1, 202.0], [33.2, 202.0], [33.3, 202.0], [33.4, 202.0], [33.5, 202.0], [33.6, 202.0], [33.7, 202.0], [33.8, 202.0], [33.9, 202.0], [34.0, 202.0], [34.1, 202.0], [34.2, 202.0], [34.3, 202.0], [34.4, 202.0], [34.5, 202.0], [34.6, 202.0], [34.7, 202.0], [34.8, 202.0], [34.9, 202.0], [35.0, 202.0], [35.1, 202.0], [35.2, 202.0], [35.3, 202.0], [35.4, 202.0], [35.5, 202.0], [35.6, 202.0], [35.7, 202.0], [35.8, 202.0], [35.9, 202.0], [36.0, 202.0], [36.1, 202.0], [36.2, 202.0], [36.3, 202.0], [36.4, 202.0], [36.5, 202.0], [36.6, 202.0], [36.7, 202.0], [36.8, 202.0], [36.9, 202.0], [37.0, 203.0], [37.1, 203.0], [37.2, 203.0], [37.3, 203.0], [37.4, 203.0], [37.5, 203.0], [37.6, 203.0], [37.7, 203.0], [37.8, 203.0], [37.9, 203.0], [38.0, 203.0], [38.1, 203.0], [38.2, 203.0], [38.3, 203.0], [38.4, 203.0], [38.5, 203.0], [38.6, 203.0], [38.7, 203.0], [38.8, 203.0], [38.9, 203.0], [39.0, 203.0], [39.1, 203.0], [39.2, 203.0], [39.3, 203.0], [39.4, 203.0], [39.5, 203.0], [39.6, 203.0], [39.7, 203.0], [39.8, 203.0], [39.9, 203.0], [40.0, 203.0], [40.1, 203.0], [40.2, 203.0], [40.3, 203.0], [40.4, 203.0], [40.5, 203.0], [40.6, 203.0], [40.7, 203.0], [40.8, 203.0], [40.9, 203.0], [41.0, 203.0], [41.1, 203.0], [41.2, 203.0], [41.3, 203.0], [41.4, 203.0], [41.5, 203.0], [41.6, 203.0], [41.7, 203.0], [41.8, 203.0], [41.9, 203.0], [42.0, 203.0], [42.1, 203.0], [42.2, 203.0], [42.3, 203.0], [42.4, 203.0], [42.5, 203.0], [42.6, 203.0], [42.7, 203.0], [42.8, 203.0], [42.9, 203.0], [43.0, 203.0], [43.1, 203.0], [43.2, 203.0], [43.3, 203.0], [43.4, 203.0], [43.5, 203.0], [43.6, 203.0], [43.7, 203.0], [43.8, 203.0], [43.9, 203.0], [44.0, 203.0], [44.1, 203.0], [44.2, 203.0], [44.3, 203.0], [44.4, 203.0], [44.5, 203.0], [44.6, 203.0], [44.7, 204.0], [44.8, 204.0], [44.9, 204.0], [45.0, 204.0], [45.1, 204.0], [45.2, 204.0], [45.3, 204.0], [45.4, 204.0], [45.5, 204.0], [45.6, 204.0], [45.7, 204.0], [45.8, 204.0], [45.9, 204.0], [46.0, 204.0], [46.1, 204.0], [46.2, 204.0], [46.3, 204.0], [46.4, 204.0], [46.5, 204.0], [46.6, 204.0], [46.7, 204.0], [46.8, 204.0], [46.9, 204.0], [47.0, 204.0], [47.1, 204.0], [47.2, 204.0], [47.3, 204.0], [47.4, 204.0], [47.5, 204.0], [47.6, 204.0], [47.7, 204.0], [47.8, 204.0], [47.9, 204.0], [48.0, 204.0], [48.1, 204.0], [48.2, 204.0], [48.3, 204.0], [48.4, 204.0], [48.5, 204.0], [48.6, 204.0], [48.7, 204.0], [48.8, 204.0], [48.9, 204.0], [49.0, 204.0], [49.1, 204.0], [49.2, 204.0], [49.3, 204.0], [49.4, 204.0], [49.5, 204.0], [49.6, 204.0], [49.7, 204.0], [49.8, 204.0], [49.9, 204.0], [50.0, 204.0], [50.1, 204.0], [50.2, 204.0], [50.3, 204.0], [50.4, 204.0], [50.5, 204.0], [50.6, 204.0], [50.7, 204.0], [50.8, 204.0], [50.9, 204.0], [51.0, 204.0], [51.1, 204.0], [51.2, 204.0], [51.3, 204.0], [51.4, 204.0], [51.5, 204.0], [51.6, 204.0], [51.7, 204.0], [51.8, 204.0], [51.9, 204.0], [52.0, 204.0], [52.1, 204.0], [52.2, 204.0], [52.3, 204.0], [52.4, 204.0], [52.5, 204.0], [52.6, 204.0], [52.7, 204.0], [52.8, 204.0], [52.9, 205.0], [53.0, 205.0], [53.1, 205.0], [53.2, 205.0], [53.3, 205.0], [53.4, 205.0], [53.5, 205.0], [53.6, 205.0], [53.7, 205.0], [53.8, 205.0], [53.9, 205.0], [54.0, 205.0], [54.1, 205.0], [54.2, 205.0], [54.3, 205.0], [54.4, 205.0], [54.5, 205.0], [54.6, 205.0], [54.7, 205.0], [54.8, 205.0], [54.9, 205.0], [55.0, 205.0], [55.1, 205.0], [55.2, 205.0], [55.3, 205.0], [55.4, 205.0], [55.5, 205.0], [55.6, 205.0], [55.7, 205.0], [55.8, 205.0], [55.9, 205.0], [56.0, 205.0], [56.1, 205.0], [56.2, 205.0], [56.3, 205.0], [56.4, 205.0], [56.5, 205.0], [56.6, 205.0], [56.7, 205.0], [56.8, 205.0], [56.9, 205.0], [57.0, 205.0], [57.1, 205.0], [57.2, 205.0], [57.3, 205.0], [57.4, 205.0], [57.5, 205.0], [57.6, 205.0], [57.7, 205.0], [57.8, 205.0], [57.9, 205.0], [58.0, 205.0], [58.1, 205.0], [58.2, 205.0], [58.3, 205.0], [58.4, 205.0], [58.5, 205.0], [58.6, 205.0], [58.7, 205.0], [58.8, 205.0], [58.9, 205.0], [59.0, 205.0], [59.1, 205.0], [59.2, 205.0], [59.3, 205.0], [59.4, 205.0], [59.5, 205.0], [59.6, 205.0], [59.7, 205.0], [59.8, 205.0], [59.9, 205.0], [60.0, 205.0], [60.1, 205.0], [60.2, 206.0], [60.3, 206.0], [60.4, 206.0], [60.5, 206.0], [60.6, 206.0], [60.7, 206.0], [60.8, 206.0], [60.9, 206.0], [61.0, 206.0], [61.1, 206.0], [61.2, 206.0], [61.3, 206.0], [61.4, 206.0], [61.5, 206.0], [61.6, 206.0], [61.7, 206.0], [61.8, 206.0], [61.9, 206.0], [62.0, 206.0], [62.1, 206.0], [62.2, 206.0], [62.3, 206.0], [62.4, 206.0], [62.5, 206.0], [62.6, 206.0], [62.7, 206.0], [62.8, 206.0], [62.9, 206.0], [63.0, 206.0], [63.1, 206.0], [63.2, 206.0], [63.3, 206.0], [63.4, 206.0], [63.5, 206.0], [63.6, 206.0], [63.7, 206.0], [63.8, 206.0], [63.9, 206.0], [64.0, 206.0], [64.1, 206.0], [64.2, 206.0], [64.3, 206.0], [64.4, 206.0], [64.5, 206.0], [64.6, 206.0], [64.7, 206.0], [64.8, 206.0], [64.9, 206.0], [65.0, 206.0], [65.1, 206.0], [65.2, 206.0], [65.3, 206.0], [65.4, 206.0], [65.5, 206.0], [65.6, 206.0], [65.7, 206.0], [65.8, 206.0], [65.9, 206.0], [66.0, 206.0], [66.1, 206.0], [66.2, 206.0], [66.3, 206.0], [66.4, 206.0], [66.5, 206.0], [66.6, 206.0], [66.7, 207.0], [66.8, 207.0], [66.9, 207.0], [67.0, 207.0], [67.1, 207.0], [67.2, 207.0], [67.3, 207.0], [67.4, 207.0], [67.5, 207.0], [67.6, 207.0], [67.7, 207.0], [67.8, 207.0], [67.9, 207.0], [68.0, 207.0], [68.1, 207.0], [68.2, 207.0], [68.3, 207.0], [68.4, 207.0], [68.5, 207.0], [68.6, 207.0], [68.7, 207.0], [68.8, 207.0], [68.9, 207.0], [69.0, 207.0], [69.1, 207.0], [69.2, 207.0], [69.3, 207.0], [69.4, 207.0], [69.5, 207.0], [69.6, 207.0], [69.7, 207.0], [69.8, 207.0], [69.9, 207.0], [70.0, 207.0], [70.1, 207.0], [70.2, 207.0], [70.3, 207.0], [70.4, 207.0], [70.5, 207.0], [70.6, 207.0], [70.7, 207.0], [70.8, 207.0], [70.9, 207.0], [71.0, 207.0], [71.1, 207.0], [71.2, 207.0], [71.3, 208.0], [71.4, 208.0], [71.5, 208.0], [71.6, 208.0], [71.7, 208.0], [71.8, 208.0], [71.9, 208.0], [72.0, 208.0], [72.1, 208.0], [72.2, 208.0], [72.3, 208.0], [72.4, 208.0], [72.5, 208.0], [72.6, 208.0], [72.7, 208.0], [72.8, 208.0], [72.9, 208.0], [73.0, 208.0], [73.1, 208.0], [73.2, 208.0], [73.3, 208.0], [73.4, 208.0], [73.5, 208.0], [73.6, 208.0], [73.7, 208.0], [73.8, 208.0], [73.9, 208.0], [74.0, 208.0], [74.1, 208.0], [74.2, 209.0], [74.3, 209.0], [74.4, 209.0], [74.5, 209.0], [74.6, 209.0], [74.7, 209.0], [74.8, 209.0], [74.9, 209.0], [75.0, 209.0], [75.1, 209.0], [75.2, 209.0], [75.3, 209.0], [75.4, 209.0], [75.5, 209.0], [75.6, 209.0], [75.7, 209.0], [75.8, 209.0], [75.9, 209.0], [76.0, 209.0], [76.1, 209.0], [76.2, 209.0], [76.3, 210.0], [76.4, 210.0], [76.5, 210.0], [76.6, 210.0], [76.7, 210.0], [76.8, 210.0], [76.9, 210.0], [77.0, 210.0], [77.1, 210.0], [77.2, 210.0], [77.3, 210.0], [77.4, 210.0], [77.5, 210.0], [77.6, 210.0], [77.7, 210.0], [77.8, 210.0], [77.9, 211.0], [78.0, 211.0], [78.1, 211.0], [78.2, 211.0], [78.3, 211.0], [78.4, 211.0], [78.5, 211.0], [78.6, 211.0], [78.7, 211.0], [78.8, 212.0], [78.9, 212.0], [79.0, 212.0], [79.1, 212.0], [79.2, 213.0], [79.3, 213.0], [79.4, 213.0], [79.5, 214.0], [79.6, 214.0], [79.7, 214.0], [79.8, 215.0], [79.9, 215.0], [80.0, 216.0], [80.1, 217.0], [80.2, 219.0], [80.3, 220.0], [80.4, 222.0], [80.5, 224.0], [80.6, 224.0], [80.7, 226.0], [80.8, 227.0], [80.9, 228.0], [81.0, 229.0], [81.1, 230.0], [81.2, 232.0], [81.3, 233.0], [81.4, 234.0], [81.5, 236.0], [81.6, 237.0], [81.7, 239.0], [81.8, 241.0], [81.9, 241.0], [82.0, 243.0], [82.1, 245.0], [82.2, 247.0], [82.3, 247.0], [82.4, 250.0], [82.5, 251.0], [82.6, 254.0], [82.7, 260.0], [82.8, 263.0], [82.9, 265.0], [83.0, 269.0], [83.1, 270.0], [83.2, 274.0], [83.3, 275.0], [83.4, 276.0], [83.5, 277.0], [83.6, 278.0], [83.7, 279.0], [83.8, 280.0], [83.9, 281.0], [84.0, 283.0], [84.1, 284.0], [84.2, 289.0], [84.3, 293.0], [84.4, 296.0], [84.5, 298.0], [84.6, 299.0], [84.7, 299.0], [84.8, 300.0], [84.9, 301.0], [85.0, 301.0], [85.1, 301.0], [85.2, 302.0], [85.3, 302.0], [85.4, 302.0], [85.5, 302.0], [85.6, 303.0], [85.7, 303.0], [85.8, 303.0], [85.9, 303.0], [86.0, 303.0], [86.1, 303.0], [86.2, 303.0], [86.3, 303.0], [86.4, 304.0], [86.5, 304.0], [86.6, 304.0], [86.7, 304.0], [86.8, 304.0], [86.9, 304.0], [87.0, 304.0], [87.1, 304.0], [87.2, 304.0], [87.3, 305.0], [87.4, 305.0], [87.5, 305.0], [87.6, 305.0], [87.7, 305.0], [87.8, 305.0], [87.9, 305.0], [88.0, 305.0], [88.1, 305.0], [88.2, 305.0], [88.3, 305.0], [88.4, 306.0], [88.5, 306.0], [88.6, 306.0], [88.7, 306.0], [88.8, 306.0], [88.9, 306.0], [89.0, 306.0], [89.1, 306.0], [89.2, 306.0], [89.3, 306.0], [89.4, 306.0], [89.5, 306.0], [89.6, 306.0], [89.7, 306.0], [89.8, 306.0], [89.9, 306.0], [90.0, 306.0], [90.1, 306.0], [90.2, 306.0], [90.3, 307.0], [90.4, 307.0], [90.5, 307.0], [90.6, 307.0], [90.7, 307.0], [90.8, 307.0], [90.9, 307.0], [91.0, 307.0], [91.1, 307.0], [91.2, 307.0], [91.3, 307.0], [91.4, 307.0], [91.5, 307.0], [91.6, 307.0], [91.7, 307.0], [91.8, 307.0], [91.9, 307.0], [92.0, 307.0], [92.1, 308.0], [92.2, 308.0], [92.3, 308.0], [92.4, 308.0], [92.5, 308.0], [92.6, 308.0], [92.7, 308.0], [92.8, 308.0], [92.9, 308.0], [93.0, 308.0], [93.1, 308.0], [93.2, 308.0], [93.3, 308.0], [93.4, 308.0], [93.5, 308.0], [93.6, 308.0], [93.7, 308.0], [93.8, 308.0], [93.9, 308.0], [94.0, 309.0], [94.1, 309.0], [94.2, 309.0], [94.3, 309.0], [94.4, 309.0], [94.5, 309.0], [94.6, 309.0], [94.7, 309.0], [94.8, 309.0], [94.9, 309.0], [95.0, 309.0], [95.1, 309.0], [95.2, 309.0], [95.3, 309.0], [95.4, 309.0], [95.5, 310.0], [95.6, 310.0], [95.7, 310.0], [95.8, 310.0], [95.9, 310.0], [96.0, 310.0], [96.1, 310.0], [96.2, 310.0], [96.3, 310.0], [96.4, 310.0], [96.5, 310.0], [96.6, 311.0], [96.7, 311.0], [96.8, 311.0], [96.9, 311.0], [97.0, 311.0], [97.1, 311.0], [97.2, 311.0], [97.3, 311.0], [97.4, 312.0], [97.5, 312.0], [97.6, 312.0], [97.7, 312.0], [97.8, 312.0], [97.9, 312.0], [98.0, 313.0], [98.1, 313.0], [98.2, 313.0], [98.3, 314.0], [98.4, 315.0], [98.5, 316.0], [98.6, 318.0], [98.7, 342.0], [98.8, 362.0], [98.9, 402.0], [99.0, 406.0], [99.1, 409.0], [99.2, 411.0], [99.3, 430.0], [99.4, 490.0], [99.5, 514.0], [99.6, 537.0], [99.7, 611.0], [99.8, 617.0], [99.9, 675.0]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
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
        data: {"result": {"minY": 1.0, "minX": 100.0, "maxY": 3012.0, "series": [{"data": [[300.0, 709.0], [600.0, 13.0], [1300.0, 1.0], [700.0, 1.0], [800.0, 1.0], [100.0, 1224.0], [200.0, 3012.0], [400.0, 29.0], [900.0, 1.0], [500.0, 9.0]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 1300.0, "title": "Response Time Distribution"}},
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
        data: {"result": {"minY": 26.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 4974.0, "series": [{"data": [[0.0, 4974.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 26.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 1.0, "title": "Synthetic Response Times Distribution"}},
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
        data: {"result": {"minY": 1.0, "minX": 1.74058512E12, "maxY": 1.0, "series": [{"data": [[1.74058518E12, 1.0], [1.74058548E12, 1.0], [1.74058602E12, 1.0], [1.74058578E12, 1.0], [1.74058512E12, 1.0], [1.74058524E12, 1.0], [1.74058554E12, 1.0], [1.74058596E12, 1.0], [1.74058566E12, 1.0], [1.74058584E12, 1.0], [1.7405853E12, 1.0], [1.7405862E12, 1.0], [1.7405856E12, 1.0], [1.7405859E12, 1.0], [1.74058542E12, 1.0], [1.74058608E12, 1.0], [1.74058572E12, 1.0], [1.74058536E12, 1.0], [1.74058614E12, 1.0]], "isOverall": false, "label": "Thread Group", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7405862E12, "title": "Active Threads Over Time"}},
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
        data: {"result": {"minY": 214.26880000000048, "minX": 1.0, "maxY": 214.26880000000048, "series": [{"data": [[1.0, 214.26880000000048]], "isOverall": false, "label": "HTTP Request", "isController": false}, {"data": [[1.0, 214.26880000000048]], "isOverall": false, "label": "HTTP Request-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 1.0, "title": "Time VS Threads"}},
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
        data : {"result": {"minY": 878.3333333333334, "minX": 1.74058512E12, "maxY": 5711.3, "series": [{"data": [[1.74058518E12, 2945.0], [1.74058548E12, 2841.6666666666665], [1.74058602E12, 2955.3333333333335], [1.74058578E12, 2955.3333333333335], [1.74058512E12, 878.3333333333334], [1.74058524E12, 2872.6666666666665], [1.74058554E12, 2893.3333333333335], [1.74058596E12, 2604.0], [1.74058566E12, 2883.0], [1.74058584E12, 2831.3333333333335], [1.7405853E12, 2893.3333333333335], [1.7405862E12, 1715.3333333333333], [1.7405856E12, 2852.0], [1.7405859E12, 2965.6666666666665], [1.74058542E12, 2872.6666666666665], [1.74058608E12, 2924.3333333333335], [1.74058572E12, 2924.3333333333335], [1.74058536E12, 2965.6666666666665], [1.74058614E12, 2893.3333333333335]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.74058518E12, 5671.5], [1.74058548E12, 5472.5], [1.74058602E12, 5691.4], [1.74058578E12, 5691.4], [1.74058512E12, 1691.5], [1.74058524E12, 5532.2], [1.74058554E12, 5572.0], [1.74058596E12, 5014.8], [1.74058566E12, 5552.1], [1.74058584E12, 5452.6], [1.7405853E12, 5572.0], [1.7405862E12, 3303.4], [1.7405856E12, 5492.4], [1.7405859E12, 5711.3], [1.74058542E12, 5532.2], [1.74058608E12, 5631.7], [1.74058572E12, 5631.7], [1.74058536E12, 5711.3], [1.74058614E12, 5572.0]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7405862E12, "title": "Bytes Throughput Over Time"}},
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
        data: {"result": {"minY": 208.27177700348435, "minX": 1.74058512E12, "maxY": 238.06349206349216, "series": [{"data": [[1.74058518E12, 209.83859649122803], [1.74058548E12, 217.29818181818175], [1.74058602E12, 208.99300699300696], [1.74058578E12, 209.32167832167832], [1.74058512E12, 215.9882352941177], [1.74058524E12, 214.611510791367], [1.74058554E12, 213.89642857142854], [1.74058596E12, 238.06349206349216], [1.74058566E12, 214.95340501792109], [1.74058584E12, 218.04379562043792], [1.7405853E12, 214.1392857142858], [1.7405862E12, 215.3554216867471], [1.7405856E12, 216.86594202898556], [1.7405859E12, 208.6968641114983], [1.74058542E12, 215.29136690647482], [1.74058608E12, 211.26148409893995], [1.74058572E12, 211.53356890459364], [1.74058536E12, 208.27177700348435], [1.74058614E12, 213.53571428571433]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7405862E12, "title": "Response Time Over Time"}},
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
        data: {"result": {"minY": 208.22648083623702, "minX": 1.74058512E12, "maxY": 237.96428571428578, "series": [{"data": [[1.74058518E12, 209.7508771929824], [1.74058548E12, 217.24], [1.74058602E12, 208.96153846153854], [1.74058578E12, 209.28671328671325], [1.74058512E12, 215.50588235294114], [1.74058524E12, 214.53237410071947], [1.74058554E12, 213.83928571428572], [1.74058596E12, 237.96428571428578], [1.74058566E12, 214.9139784946237], [1.74058584E12, 218.00000000000003], [1.7405853E12, 214.11071428571432], [1.7405862E12, 215.31927710843374], [1.7405856E12, 216.83695652173918], [1.7405859E12, 208.6550522648083], [1.74058542E12, 215.2553956834533], [1.74058608E12, 211.2438162544169], [1.74058572E12, 211.48056537102462], [1.74058536E12, 208.22648083623702], [1.74058614E12, 213.48571428571435]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7405862E12, "title": "Latencies Over Time"}},
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
        data: {"result": {"minY": 0.5120481927710843, "minX": 1.74058512E12, "maxY": 1.694117647058824, "series": [{"data": [[1.74058518E12, 1.0877192982456139], [1.74058548E12, 0.5818181818181816], [1.74058602E12, 0.5979020979020983], [1.74058578E12, 0.6118881118881114], [1.74058512E12, 1.694117647058824], [1.74058524E12, 0.9784172661870506], [1.74058554E12, 0.5892857142857146], [1.74058596E12, 0.5555555555555558], [1.74058566E12, 0.6272401433691758], [1.74058584E12, 0.5802919708029196], [1.7405853E12, 0.9535714285714288], [1.7405862E12, 0.5120481927710843], [1.7405856E12, 0.5181159420289854], [1.7405859E12, 0.519163763066202], [1.74058542E12, 0.6474820143884888], [1.74058608E12, 0.5936395759717312], [1.74058572E12, 0.625441696113074], [1.74058536E12, 0.6864111498257834], [1.74058614E12, 0.5928571428571423]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7405862E12, "title": "Connect Time Over Time"}},
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
        data: {"result": {"minY": 123.0, "minX": 1.74058512E12, "maxY": 1331.0, "series": [{"data": [[1.74058518E12, 411.0], [1.74058548E12, 404.0], [1.74058602E12, 537.0], [1.74058578E12, 424.0], [1.74058512E12, 812.0], [1.74058524E12, 611.0], [1.74058554E12, 675.0], [1.74058596E12, 1331.0], [1.74058566E12, 620.0], [1.74058584E12, 710.0], [1.7405853E12, 325.0], [1.7405862E12, 614.0], [1.7405856E12, 493.0], [1.7405859E12, 316.0], [1.74058542E12, 673.0], [1.74058608E12, 317.0], [1.74058572E12, 342.0], [1.74058536E12, 409.0], [1.74058614E12, 617.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.74058518E12, 305.0], [1.74058548E12, 306.0], [1.74058602E12, 306.0], [1.74058578E12, 304.3], [1.74058512E12, 301.20000000000005], [1.74058524E12, 306.0], [1.74058554E12, 307.0], [1.74058596E12, 310.0], [1.74058566E12, 307.0], [1.74058584E12, 307.0], [1.7405853E12, 307.0], [1.7405862E12, 308.0], [1.7405856E12, 307.0], [1.7405859E12, 307.0], [1.74058542E12, 307.0], [1.74058608E12, 306.6], [1.74058572E12, 306.0], [1.74058536E12, 304.0], [1.74058614E12, 305.9]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.74058518E12, 325.0199999999994], [1.74058548E12, 313.48], [1.74058602E12, 422.5199999999995], [1.74058578E12, 403.16999999999996], [1.74058512E12, 812.0], [1.74058524E12, 455.8299999999975], [1.74058554E12, 427.1499999999998], [1.74058596E12, 766.1199999999997], [1.74058566E12, 403.99999999999994], [1.74058584E12, 440.0], [1.7405853E12, 314.38], [1.7405862E12, 555.7100000000011], [1.7405856E12, 381.9700000000007], [1.7405859E12, 311.24], [1.74058542E12, 404.3099999999998], [1.74058608E12, 312.16], [1.74058572E12, 312.16], [1.74058536E12, 324.52000000000044], [1.74058614E12, 421.34999999999985]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.74058518E12, 308.7], [1.74058548E12, 309.0], [1.74058602E12, 308.0], [1.74058578E12, 309.0], [1.74058512E12, 306.0], [1.74058524E12, 309.0], [1.74058554E12, 311.95], [1.74058596E12, 520.6499999999999], [1.74058566E12, 309.0], [1.74058584E12, 310.0], [1.7405853E12, 308.0], [1.7405862E12, 311.65], [1.7405856E12, 310.15], [1.7405859E12, 309.0], [1.74058542E12, 310.0], [1.74058608E12, 309.0], [1.74058572E12, 308.8], [1.74058536E12, 309.19999999999993], [1.74058614E12, 310.0]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.74058518E12, 129.0], [1.74058548E12, 123.0], [1.74058602E12, 126.0], [1.74058578E12, 126.0], [1.74058512E12, 131.0], [1.74058524E12, 126.0], [1.74058554E12, 123.0], [1.74058596E12, 124.0], [1.74058566E12, 127.0], [1.74058584E12, 128.0], [1.7405853E12, 125.0], [1.7405862E12, 127.0], [1.7405856E12, 127.0], [1.7405859E12, 124.0], [1.74058542E12, 123.0], [1.74058608E12, 127.0], [1.74058572E12, 126.0], [1.74058536E12, 125.0], [1.74058614E12, 126.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.74058518E12, 204.0], [1.74058548E12, 204.0], [1.74058602E12, 204.0], [1.74058578E12, 204.0], [1.74058512E12, 204.0], [1.74058524E12, 204.0], [1.74058554E12, 204.0], [1.74058596E12, 204.0], [1.74058566E12, 204.0], [1.74058584E12, 204.5], [1.7405853E12, 204.0], [1.7405862E12, 204.0], [1.7405856E12, 205.0], [1.7405859E12, 204.0], [1.74058542E12, 204.5], [1.74058608E12, 204.0], [1.74058572E12, 204.0], [1.74058536E12, 204.0], [1.74058614E12, 204.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7405862E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
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
    data: {"result": {"minY": 138.0, "minX": 1.0, "maxY": 530.0, "series": [{"data": [[4.0, 206.0], [1.0, 530.0], [2.0, 408.5], [5.0, 204.0], [6.0, 185.0], [3.0, 209.0], [7.0, 138.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 7.0, "title": "Response Time Vs Request"}},
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
    data: {"result": {"minY": 138.0, "minX": 1.0, "maxY": 530.0, "series": [{"data": [[4.0, 206.0], [1.0, 530.0], [2.0, 408.5], [5.0, 204.0], [6.0, 184.5], [3.0, 209.0], [7.0, 138.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 7.0, "title": "Latencies Vs Request"}},
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
        data: {"result": {"minY": 1.4333333333333333, "minX": 1.74058512E12, "maxY": 4.783333333333333, "series": [{"data": [[1.74058518E12, 4.75], [1.74058548E12, 4.583333333333333], [1.74058602E12, 4.766666666666667], [1.74058578E12, 4.766666666666667], [1.74058512E12, 1.4333333333333333], [1.74058524E12, 4.633333333333334], [1.74058554E12, 4.666666666666667], [1.74058596E12, 4.2], [1.74058566E12, 4.65], [1.74058584E12, 4.566666666666666], [1.7405853E12, 4.666666666666667], [1.7405862E12, 2.75], [1.7405856E12, 4.6], [1.7405859E12, 4.783333333333333], [1.74058542E12, 4.633333333333334], [1.74058608E12, 4.716666666666667], [1.74058572E12, 4.716666666666667], [1.74058536E12, 4.783333333333333], [1.74058614E12, 4.666666666666667]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7405862E12, "title": "Hits Per Second"}},
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
        data: {"result": {"minY": 1.4166666666666667, "minX": 1.74058512E12, "maxY": 4.783333333333333, "series": [{"data": [[1.74058518E12, 4.75], [1.74058548E12, 4.583333333333333], [1.74058602E12, 4.766666666666667], [1.74058578E12, 4.766666666666667], [1.74058512E12, 1.4166666666666667], [1.74058524E12, 4.633333333333334], [1.74058554E12, 4.666666666666667], [1.74058596E12, 4.2], [1.74058566E12, 4.65], [1.74058584E12, 4.566666666666666], [1.7405853E12, 4.666666666666667], [1.7405862E12, 2.7666666666666666], [1.7405856E12, 4.6], [1.7405859E12, 4.783333333333333], [1.74058542E12, 4.633333333333334], [1.74058608E12, 4.716666666666667], [1.74058572E12, 4.716666666666667], [1.74058536E12, 4.783333333333333], [1.74058614E12, 4.666666666666667]], "isOverall": false, "label": "201", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7405862E12, "title": "Codes Per Second"}},
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
        data: {"result": {"minY": 1.4166666666666667, "minX": 1.74058512E12, "maxY": 4.783333333333333, "series": [{"data": [[1.74058518E12, 4.75], [1.74058548E12, 4.583333333333333], [1.74058602E12, 4.766666666666667], [1.74058578E12, 4.766666666666667], [1.74058512E12, 1.4166666666666667], [1.74058524E12, 4.633333333333334], [1.74058554E12, 4.666666666666667], [1.74058596E12, 4.2], [1.74058566E12, 4.65], [1.74058584E12, 4.566666666666666], [1.7405853E12, 4.666666666666667], [1.7405862E12, 2.7666666666666666], [1.7405856E12, 4.6], [1.7405859E12, 4.783333333333333], [1.74058542E12, 4.633333333333334], [1.74058608E12, 4.716666666666667], [1.74058572E12, 4.716666666666667], [1.74058536E12, 4.783333333333333], [1.74058614E12, 4.666666666666667]], "isOverall": false, "label": "HTTP Request-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7405862E12, "title": "Transactions Per Second"}},
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
        data: {"result": {"minY": 1.4166666666666667, "minX": 1.74058512E12, "maxY": 4.783333333333333, "series": [{"data": [[1.74058518E12, 4.75], [1.74058548E12, 4.583333333333333], [1.74058602E12, 4.766666666666667], [1.74058578E12, 4.766666666666667], [1.74058512E12, 1.4166666666666667], [1.74058524E12, 4.633333333333334], [1.74058554E12, 4.666666666666667], [1.74058596E12, 4.2], [1.74058566E12, 4.65], [1.74058584E12, 4.566666666666666], [1.7405853E12, 4.666666666666667], [1.7405862E12, 2.7666666666666666], [1.7405856E12, 4.6], [1.7405859E12, 4.783333333333333], [1.74058542E12, 4.633333333333334], [1.74058608E12, 4.716666666666667], [1.74058572E12, 4.716666666666667], [1.74058536E12, 4.783333333333333], [1.74058614E12, 4.666666666666667]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7405862E12, "title": "Total Transactions Per Second"}},
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

