 var cm = false;

 function month_chart(ch) {
            var s1 = [[1, 130], [2, 132.6], [3, 135], [4, 137.5]]; //나
            var s2 = [[1, 140.5], [2, 141.6], [3, 145.8], [4, 146.5]]; //학교
            var s3 = [[1, 141.8], [2, 143.8], [3, 147.7], [4, 150.6]]; //전체

            var p = 141.36;//표준 신장 값

            var s4 = [[1, p], [2, p], [3, p], [4, p],[5, p]]; //표준

            var ticks = [[1, "9월"], [2, "10월"], [3, "11월"], [4, "12월"]];
            if (ch == false) { var c = true; } else { var c = false; }

            plot1 = $.jqplot("chart_height_detail", [s4, s3, s2, s1], {
                // Turns on animatino for all series in this plot.
                animate: c,
                //  stackSeries:true,
                // Will animate plot on calls to plot1.replot({resetAxes:true})
                animateReplot: true,
                cursor: {
                    show: true,
                    zoom: true,
                    looseZoom: true,
                    showTooltip: false
                }, legend: {
                    show: false,
                    location: 'se',
                    //    placement: 'outside'
                },
                series: [
                     {
                         pointLabels: {
                             show: false,
                             location: 'n'
                         },
                         renderer: $.jqplot.BarRenderer,
                         showHighlight: false,
                         shadow: false,
                         yaxis: 'yaxis',
                         rendererOptions: {
                             // Speed up the animation a little bit.
                             // This is a number of milliseconds.  
                             // Default for bar series is 3000.  
                             barDirection: 'horizontal',
                             animation: {
                                 speed: 1800
                             },                             
                             color:'#afcc12',
                             barWidth: 20,
                             barPadding: -20,
                             barMargin: 0,
                             highlightMouseOver: false
                         }
                     },
                    {
                        pointLabels: {
                            show: false
                        },
                        renderer: $.jqplot.BarRenderer,
                        showHighlight: false,
                        shadow: false,
                        yaxis: 'yaxis',
                        rendererOptions: {
                            // Speed up the animation a little bit.
                            // This is a number of milliseconds.  
                            // Default for bar series is 3000.  
                            animation: {
                                speed: 2500
                            },
                            color: '#fc7736',
                            barWidth: 15,
                            barPadding: 0,
                            barMargin: 0,
                            highlightMouseOver: true
                        }
                    }, {
                        pointLabels: {
                            show: false
                        },
                        renderer: $.jqplot.BarRenderer,
                        showHighlight: false,
                        fillAndStroke: true,
                        shadow: false,
                        yaxis: 'yaxis',
                        rendererOptions: {
                            // Speed up the animation a little bit.
                            // This is a number of milliseconds.  
                            // Default for bar series is 3000.  
                            animation: {
                                speed: 2000
                            },
                            color: '#00ff7f',
                            barWidth: 15,
                            barPadding: 0,
                            barMargin: 0,
                            highlightMouseOver: true
                        }
                    }, {
                        pointLabels: {
                            show: false
                        },
                        renderer: $.jqplot.BarRenderer,
                        showHighlight: false,
                        shadow: false,
                        yaxis: 'yaxis',
                        rendererOptions: {
                            // Speed up the animation a little bit.
                            // This is a number of milliseconds.  
                            // Default for bar series is 3000.  
                            animation: {
                                speed: 2200
                            },
                            color: '#3fc6f3',
                            barWidth: 15,
                            barPadding: 0,
                            barMargin: 0,
                            highlightMouseOver: true
                        }
                    }
                ],
                axesDefaults: {
                    pad: 0
                },grid: {             
                    background: '#ffffff',
                    borderColor: '#000000',
                    borderWidth: 0.0,  
                    shadow : false     
                },
                axes: {
                    // These options will set up the x axis like a category axis.
                    xaxis: {
                        ticks: ticks,
                        tickInterval: 1,
                        drawMajorGridlines: false,
                        drawMinorGridlines: true,
                        drawMajorTickMarks: false,
                        rendererOptions: {
                            tickInset: 1,
                            minorTicks: 1
                        },
                        
                    },
                    yaxis: {
                        tickOptions: {
                            formatString: "%'d",
                            showGridline: false 
                        },
                        rendererOptions: {
                            alignTicks: true,
                            forceTickAt0: true
                        },min: 50, max:180
                    },
                    y2axis: {
                        tickOptions: {
                            formatString: "%'d",
                            showGridline: false 
                        },
                        rendererOptions: {
                            // align the ticks on the y2 axis with the y axis.
                            alignTicks: true,
                            forceTickAt0: true
                        }
                    }
                }

                // ,
                // highlighter: {
                //     show: true, 
                //     showLabel: true, 
                //     tooltipAxes: 'y',
                //     sizeAdjust: 7.5 , tooltipLocation : 'ne'
                // }
            });
            $("#chart_height_month").bind('jqplotDataHighlight', function (ev, seriesIndex, pointIndex, data) {
            }
          );

            $("#chart_height_month").bind('jqplotDataClick', function (ev, seriesIndex, pointIndex, data) {
                var mouseX = ev.pageX;
                var mouseY = ev.pageY - 20;
                var color = 'rgb(50%,50%,100%)';
                var cssObj = {
                    'position': 'absolute',
                    'font-weight': 'bold',
                    'left': mouseX + 'px', //usually needs more offset here
                    'top': mouseY + 'px'
                };
                $("#tooltip1b1").css(cssObj);
                $("#tooltip1b1").html('<span style="font-size:14px;font-weight:bold;color:' + color + ';">' + '나 : ' + data[1] + '<br>' + '학교 : ' + plot1.data.indexOf[seriesIndex] + '<br>' + '전체 : ' + data[0] + '<br>' + '</span>');
                $("#tooltip1b1").show();
            }
            );

            $("#chart_height_month").bind('jqplotDataUnhighlight', function (ev) {
                $("#info2").html("0");
            }
            );
        }