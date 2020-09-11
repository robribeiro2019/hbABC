
window.onload = function() {
	
	var dataArray1 = [];
    $.ajax({
        async: false,
        url: "char",
        dataType:"json",
        success: function(dado) {
  	      
  	      $.each(dado, function(index, teste){
  	    	  var date = new Date(teste.date);
  	    	dataArray1.push([   
  	    		
  	    		date  , 
  	    		
  	    		Number(teste.close.replace('.','')),  
  	    		
  	    		Number(teste.open.replace('.',''))
  	    		
  	    		
  	    		]);
  	      });
  	    drawTrendlines(dataArray1);
        }
      });
    
};


google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawTrendlines);

function drawTrendlines(dataArray1) {

      var data = new google.visualization.DataTable();
      data.addColumn('date', 'X');
      data.addColumn('number', 'CLOSE');
      data.addColumn('number', 'OPEN');
      
      data.addRows(dataArray1);

      var options = {
    	        hAxis: {
    	          title: 'Period Time'
    	        },
    	        vAxis: {
    	          title: 'Close and Open Values'
    	        },
    	        colors: ['#AB0D06', '#007329'],
    	        trendlines: {
    	          0: {type: 'exponential', color: '#333', opacity: 1},
    	          1: {type: 'linear', color: '#111', opacity: .3}
    	        },
    	        chartArea: {
    				width:'90%',
    				height:'60%'
    			},
    			legend: {
    				position:'none'
    			},
    			explorer: {
    				maxZoomOut:2,
    				keepInBounds: true
    			}
    	        
    	      };

      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }