
window.onload = function() {
	
	var dataArray = [];
    $.ajax({
        async: false,
        url: "char",
        dataType:"json",
        success: function(quote) {
	  	      $.each(quote.dias, function(idx1, diaOperacao){
	      			var date = new Date(diaOperacao.date);
					var i = 3.0;
		  	    	dataArray.push([
		  	    		date, 
		  	    		Number(diaOperacao.open),  
		  	    		Number(diaOperacao.close),
		  	    		Number(diaOperacao.ema9),
		  	    		Number(diaOperacao.ema12),
		  	    		Number(diaOperacao.ema26)
		  	    	]);
	  	      });
	  	      drawChart(dataArray);
  	      }
      });
    
};


google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawChart);

function drawChart(dataArray) {

      var data = new google.visualization.DataTable();
      data.addColumn('date',   'DATA');
      data.addColumn('number', 'ABERTURA');
      data.addColumn('number', 'FECHAMENTO');
      data.addColumn('number', 'EMA9');
      data.addColumn('number', 'EMA12');
      data.addColumn('number', 'EMA26');
      
      data.addRows(dataArray);

      var options = {
    	        hAxis: {
    	          title: 'Period Time'
    	        },
    	        vAxis: {
    	          title: 'Close and Open Values'
    	        },
    	        colors: ['#008000', '#FF0000', '#FFFF00', '#FFA500', '#0000FF'],
    	        chartArea: {
    				width:'70%',
    				height:'60%'
    			},
    			explorer: {
    				maxZoomOut:50,
    				keepInBounds: true
    			}
    	        
    	      };
    	      
		      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
		      chart.draw(data, options);
    }