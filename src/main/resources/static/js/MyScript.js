//var obj = await (await fetch(url)).json();
var obj = [{ "Room_id": "1", "light_id": "1", "status": "ON", "level": "87.", "a": "47", "b": "83" }, { "Room_id": "1", "light_id": "2", "status": "OFF", "level": "90", "a": "4.5", "b": "1.5" }, { "Room_id": "2", "light_id": "1", "status": "ON" , "level": "90", "a": "-35", "b": "70"}];

 //let response = await fetch('localhost:8080/api/lights');
 //let obj = await response.json();

var col = [];
var Hist = document.createElement("Hist");
Hist = ' ';
    for (var i = 0; i < obj.length; i++) {
            for (var key in obj[i]) {
                if (col.indexOf(key) === -1) {
                    col.push(key);
                }
            }
        }
function rgb2lab(rgb){

         var r = rgb[0] / 255,
            g = rgb[1] / 255,
            b = rgb[2] / 255,
           x, y, z;

        r = (r > 0.04045) ? Math.pow((r + 0.055) / 1.055, 2.4) : r / 12.92;
         g = (g > 0.04045) ? Math.pow((g + 0.055) / 1.055, 2.4) : g / 12.92;
         b = (b > 0.04045) ? Math.pow((b + 0.055) / 1.055, 2.4) : b / 12.92;

        x = (r * 0.4124 + g * 0.3576 + b * 0.1805) / 0.95047;
        y = (r * 0.2126 + g * 0.7152 + b * 0.0722) / 1.00000;
        z = (r * 0.0193 + g * 0.1192 + b * 0.9505) / 1.08883;

        x = (x > 0.008856) ? Math.pow(x, 1/3) : (7.787 * x) + 16/116;
         y = (y > 0.008856) ? Math.pow(y, 1/3) : (7.787 * y) + 16/116;
         z = (z > 0.008856) ? Math.pow(z, 1/3) : (7.787 * z) + 16/116;

        return [(116 * y) - 16, 500 * (x - y), 200 * (y - z)]
}
function lab2rgb(lab){
  var y = (lab[0] + 16) / 116,
      x = lab[1] / 500 + y,
      z = y - lab[2] / 200,
      r, g, b;

  x = 0.95047 * ((x * x * x > 0.008856) ? x * x * x : (x - 16/116) / 7.787);
  y = 1.00000 * ((y * y * y > 0.008856) ? y * y * y : (y - 16/116) / 7.787);
  z = 1.08883 * ((z * z * z > 0.008856) ? z * z * z : (z - 16/116) / 7.787);

  r = x *  3.2406 + y * -1.5372 + z * -0.4986;
  g = x * -0.9689 + y *  1.8758 + z *  0.0415;
  b = x *  0.0557 + y * -0.2040 + z *  1.0570;

  r = (r > 0.0031308) ? (1.055 * Math.pow(r, 1/2.4) - 0.055) : 12.92 * r;
  g = (g > 0.0031308) ? (1.055 * Math.pow(g, 1/2.4) - 0.055) : 12.92 * g;
  b = (b > 0.0031308) ? (1.055 * Math.pow(b, 1/2.4) - 0.055) : 12.92 * b;

  return [Math.max(0, Math.min(1, r)) * 255,
          Math.max(0, Math.min(1, g)) * 255,
          Math.max(0, Math.min(1, b)) * 255]
}
 function Eteindre(i){
	obj[i][col[2]]= 'OFF';
	CreateTableFromJSON();
	var HistContainer = document.getElementById("showHist");
	Hist='You turned off the light  '+obj[i][col[1]]+ ' in the room ' +obj[i][col[0]] + '</br>' + Hist  ;
	HistContainer.innerHTML = Hist;
	

}
function Allumer(i){
	obj[i][col[2]]= 'ON';
	CreateTableFromJSON();
		var HistContainer = document.getElementById("showHist");
	Hist='You turned on the light  '+obj[i][col[1]]+ ' in the room ' +obj[i][col[0]] + '</br>' + Hist  ;
	HistContainer.innerHTML = Hist;
	/*	var XHR = new XMLHttpRequest();
	XHR.addEventListener('load', function(event) {
    alert('Données envoyées.');
	});
	XHR.addEventListener('error', function(event) {
    alert('Oups! Quelque chose s\'est mal passé.');
  });
  var id = obj[i][1];
  XHR.open('POST', 'localhost:8080/api/lights/'+id+'switch');
  XRH.send('ON');
*/
} 
function ColorUpdate(i){
	var value=document.getElementById('colorWell'+i).value;
	var R = value.substring(1,3);
	var G = value.substring(3,5);
	var B = value.substring(5,7);
	var RGB=[R,G,B];
	RGB[0]= parseInt(RGB[0],16);
	RGB[1]= parseInt(RGB[1],16);
	RGB[2]= parseInt(RGB[2],16);
	RGB[0]= parseInt(RGB[0],10);
	RGB[1]= parseInt(RGB[1],10);
	RGB[2]= parseInt(RGB[2],10); 
	var LAB = rgb2lab(RGB);
	obj[i][col[3]]=LAB[0];
	obj[i][col[4]]=LAB[1];
	obj[i][col[5]]=LAB[2];
	CreateTableFromJSON();
		var HistContainer = document.getElementById("showHist");
	Hist='You changed the color of the lamp '+obj[i][col[1]]+ ' in the room ' +obj[i][col[0]] + '</br>' + Hist  ;
	HistContainer.innerHTML = Hist;
	//SendUpdate;
}
function CreateTableFromJSON() {
        

        // EXTRACT VALUE FOR HTML HEADER.
        // ('Room_id', 'light_id', 'Cstatus','level' , 'a' and 'b')
        
        // CREATE DYNAMIC TABLE.
        var table = document.createElement("table");

        // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

        var tr = table.insertRow(-1);                   // TABLE ROW.


		var th = document.createElement("th");      // TABLE HEADER.
            th.innerHTML = "Room ID";
            tr.appendChild(th);
		var th = document.createElement("th");      // TABLE HEADER.
            th.innerHTML = "Light ID";
            tr.appendChild(th);
		var th = document.createElement("th");      // TABLE HEADER.
            th.innerHTML = "Status";
            tr.appendChild(th);
		 var th = document.createElement("th");      // TABLE HEADER.
            th.innerHTML = "Action";
            tr.appendChild(th);
		var th = document.createElement("th");      // TABLE HEADER.
            th.innerHTML = "Color";
            tr.appendChild(th);
       

        // ADD JSON DATA TO THE TABLE AS ROWS.
        for (var i = 0; i < obj.length; i++) {

            tr = table.insertRow(-1);
			// Add the  first values of the JSON we only put the Room_id and the light id
            for (var j = 0; j < 2; j++) {
                var tabCell = tr.insertCell(-1);
                tabCell.innerHTML = obj[i][col[j]];
            }
			var tabCell = tr.insertCell(-1);
                tabCell.innerHTML = obj[i][col[2]];
            var tabCell = tr.insertCell(-1);
            if (obj[i][col[2]] == 'ON') {
                tabCell.innerHTML = '<button class="button1" style="vertical-align:middle" onclick="Eteindre('+i+')" ><span>Eteindre</span></button>'; //

            }
            if (obj[i][col[2]] == 'OFF') {
                tabCell.innerHTML = '<button class="button2" style="vertical-align:middle" onclick="Allumer('+i+')" ><span>Allumer</span></button>'; //
            }
            tr.appendChild(tabCell);
			var L = obj[i][col[3]];
			var a = obj[i][col[4]];
			var b = obj[i][col[5]];
			var LAB = [L,a,b];
			LAB[0]= parseFloat(LAB[0]);
			LAB[1]= parseFloat(LAB[1]);
			LAB[2]= parseFloat(LAB[2]);
			var RGB = lab2rgb(LAB);
			tabCell = tr.insertCell(-1);
			//We need to hav entire values
			RGB[0]= parseInt(RGB[0],10);
			RGB[1]= parseInt(RGB[1],10);
			RGB[2]= parseInt(RGB[2],10);
			// we must convet it in number base 16 
			for (var k = 0 ; k<3;k++){
				if (RGB[k]<16){
					RGB[k] = RGB[k].toString(16);
					RGB[k]='0'+RGB[k]; // If we don't do this, #ff0000 would be transformed into #ff00 that cannot match with the format
				}
				else{
					RGB[k] = RGB[k].toString(16);
				}
			}	
			RGB  = '#'+RGB[0]+RGB[1]+RGB[2];
			tabCell.innerHTML = '<input type="color" class="ColorView" value='+RGB+'  id="colorWell'+i+'" ><button class="button3" onclick="ColorUpdate('+i+')" >Go</button>'; //
			tr.appendChild(tabCell);
			
			
        }

        // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
        var divContainer = document.getElementById("showData");
        divContainer.innerHTML = "";
        divContainer.appendChild(table);
		}
		