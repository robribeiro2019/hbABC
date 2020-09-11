
window.onload = function() {
	
	var dataArray = [];
    $.ajax({
        async: false,
        url: "char",
        dataType:"json",
        success: function(mapa) {
	  	      $.each(mapa, function(idx1, mapaAux){
	  	      		$.each(mapaAux.dia, function(idx2, diasAux){
	  	      			var date = new Date(diasAux.date);
	  	      			
			  	    	dataArray.push([
			  	    		date, 
			  	    		Number(diasAux.close),  
			  	    		Number(diasAux.open)
			  	    	]);
	  	      		});
	  	      });
	  	      drawTrendlines(dataArray);
  	      }
      });
    
};


google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawTrendlines);

function drawTrendlines(dataArray) {

      var data = new google.visualization.DataTable();
      data.addColumn('date', 'X');
      data.addColumn('number', 'CLOSE');
      data.addColumn('number', 'OPEN');
      
      data.addRows(dataArray);

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