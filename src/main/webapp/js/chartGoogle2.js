      google.charts.load('current', {packages:['corechart']});
      google.charts.setOnLoadCallback(drawStuff);

        function drawStuff() {
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Country');
          data.addColumn('number', 'GDP');
          data.addRows([
            ['US', 16768100],
            ['China', 9181204],
            ['Japan', 4898532],
            ['Germany', 3730261],
            ['France', 2678455]
          ]);

         var options = {
           title: 'MACD EMA 9, 12 and 26,',
           width: 1000,
           height: 300,
           legend: 'none',
           bar: {groupWidth: '95%'},
           vAxis: { gridlines: { count: 4 } }
         };

         var chart = new google.visualization.ColumnChart(document.getElementById('number_format_chart'));
         chart.draw(data, options);

         document.getElementById('format-select').onchange = function() {
           options['vAxis']['format'] = this.value;
           chart.draw(data, options);
         };
      };