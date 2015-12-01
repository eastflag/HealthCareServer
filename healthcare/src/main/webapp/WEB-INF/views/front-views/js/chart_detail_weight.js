 var c1 = false;
 var c2 = false;
 var c3 = false;

 var c4 = false;
 var c5 = false;
 var c6 = false;

  function month_chart(ch) {
            var s1 = [[1, 39.4], [2, 41.8], [3, 45.3], [4, 47]]; //나
            var s2 = [[1, 38.2], [2, 42.8], [3, 43.5], [4, 45.7]]; //학교
            var s3 = [[1, 37.6], [2, 39.5], [3, 44.1], [4, 46.2]]; //전체

            var p = 47.6;//표준 신장 값

            var s4 = [[1, p], [2, p], [3, p], [4, p]]; //표준

            var ticks = [[1, "9월"], [2, "10월"], [3, "11월"], [4, "12월"]];
            if (ch == false) { var c = true; } else { var c = false; }

            plot1 = $.jqplot("chart_height_month", [s4, s3, s2, s1], {
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
                             barWidth: 5,
                             barPadding: 0,
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
                        shadow: true,
                        yaxis: 'yaxis',
                        rendererOptions: {
                            // Speed up the animation a little bit.
                            // This is a number of milliseconds.  
                            // Default for bar series is 3000.  
                            animation: {
                                speed: 2500
                            },
                            color: '#afcc12',
                            barWidth: 30,
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
                        yaxis: 'yaxis',
                        rendererOptions: {
                            // Speed up the animation a little bit.
                            // This is a number of milliseconds.  
                            // Default for bar series is 3000.  
                            animation: {
                                speed: 2000
                            },
                            color: '#fc7736',
                            barWidth: 30,
                            barPadding: -60,
                            barMargin: 0,
                            highlightMouseOver: true
                        }
                    }, {
                        pointLabels: {
                            show: false
                        },
                        renderer: $.jqplot.BarRenderer,
                        showHighlight: false,
                        yaxis: 'yaxis',
                        rendererOptions: {
                            // Speed up the animation a little bit.
                            // This is a number of milliseconds.  
                            // Default for bar series is 3000.  
                            animation: {
                                speed: 2200
                            },
                            color: '#eb2b2a',
                            barWidth: 30,
                            barPadding: -40,
                            barMargin: 0,
                            highlightMouseOver: true
                        }
                    }
                ],
                axesDefaults: {
                    pad: 0
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
                            tickInset: 0.5,
                            minorTicks: 1
                        }
                    },
                    yaxis: {
                        tickOptions: {
                            formatString: "%'d"
                        },
                        rendererOptions: {
                            alignTicks: true,
                            forceTickAt0: true
                        }
                    },
                    y2axis: {
                        tickOptions: {
                            formatString: "%'d"
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
                $("#tooltip1b1").html('<span style="font-size:14px;font-weight:bold;color:' + color + ';">' + '나 : 39' + '<br>' + '학교 : 38' + '<br>' + '전체 : 37'+ '<br>' + '</span>');
                $("#tooltip1b1").show();
            }
            );

            $("#chart_height_month").bind('jqplotDataUnhighlight', function (ev) {
                $("#info2").html("0");
            }
            );
        }
  function year_chart(ch) {
            var s1 = [[1, 24.3], [2, 26.7], [3, 28.4], [4, 31.5]]; //나
            var s2 = [[1, 25.4], [2, 26.8], [3, 27.4], [4, 29.7]]; //학교
            var s3 = [[1, 26.3], [2, 25.7], [3, 26.4], [4, 27.8]]; //전체

            var p = 47.6;//표준 신장 값

            var s4 = [[1, p], [2, p], [3, p], [4, p]]; //표준

            var ticks = [[1, "1월"], [2, "2월"], [3, "3월"], [4, "4월"]];
            if (ch == false) { var c = true; } else { var c = false; }

            plot1 = $.jqplot("chart_height_year", [s4, s3, s2, s1], {
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
                             color: '#afcc12',
                             barWidth: 5,
                             barPadding: 0,
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
                        shadow: true,
                        yaxis: 'yaxis',
                        rendererOptions: {
                            // Speed up the animation a little bit.
                            // This is a number of milliseconds.  
                            // Default for bar series is 3000.  
                            animation: {
                                speed: 2500
                            },
                            color: '#afcc12',
                            barWidth: 30,
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
                        yaxis: 'yaxis',
                        rendererOptions: {
                            // Speed up the animation a little bit.
                            // This is a number of milliseconds.  
                            // Default for bar series is 3000.  
                            animation: {
                                speed: 2000
                            },
                            color: '#fc7736',
                            barWidth: 30,
                            barPadding: -60,
                            barMargin: 0,
                            highlightMouseOver: true
                        }
                    }, {
                        pointLabels: {
                            show: false
                        },
                        renderer: $.jqplot.BarRenderer,
                        showHighlight: false,
                        yaxis: 'yaxis',
                        rendererOptions: {
                            // Speed up the animation a little bit.
                            // This is a number of milliseconds.  
                            // Default for bar series is 3000.  
                            animation: {
                                speed: 2200
                            },
                            color: '#eb2b2a',
                            barWidth: 30,
                            barPadding: -40,
                            barMargin: 0,
                            highlightMouseOver: true
                        }
                    }
                ],
                axesDefaults: {
                    pad: 0
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
                            tickInset: 0.5,
                            minorTicks: 1
                        }
                    },
                    yaxis: {
                        tickOptions: {
                            formatString: "%'d"
                        },
                        rendererOptions: {
                            forceTickAt0: true
                        }
                    },
                    y2axis: {
                        tickOptions: {
                            formatString: "%'d"
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
            $("#chart_height_year").bind('jqplotDataHighlight', function (ev, seriesIndex, pointIndex, data) {
            }
          );

            $("#chart_height_year").bind('jqplotDataClick', function (ev, seriesIndex, pointIndex, data) {
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
                $("#tooltip1b1").html('<span style="font-size:14px;font-weight:bold;color:' + color + ';">' + '나 : 24' + '<br>' + '학교 : 25'+ '<br>' + '전체 : 26'+ '<br>' + '</span>');
                $("#tooltip1b1").show();
            }
            );

            $("#chart_height_year").bind('jqplotDataUnhighlight', function (ev) {
                $("#info2").html("0");
            }
            );
        }
 function this_chart(ch) {
            var s1 = [[1, 28.4], [2, 33.1], [3, 35.4], [4, 36.2]]; //나
            var s2 = [[1, 27.4], [2, 29.8], [3, 31.9], [4, 33.7]]; //학교
            var s3 = [[1, 28.6], [2, 30.4], [3, 32.9], [4, 33.7]]; //전체

            var p = 47.6;//표준 신장 값

            var s4 = [[1, p], [2, p], [3, p], [4, p]]; //표준

            var ticks = [[1, "2002년"], [2, "2003년"], [3, "2004년"], [4, "2005년"]];
            if (ch == false) { var c = true; } else { var c = false; }

            plot1 = $.jqplot("chart_height_this", [s4, s3, s2, s1], {
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
                             color: '#afcc12',
                             barWidth: 5,
                             barPadding: 0,
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
                        shadow: true,
                        yaxis: 'yaxis',
                        rendererOptions: {
                            // Speed up the animation a little bit.
                            // This is a number of milliseconds.  
                            // Default for bar series is 3000.  
                            animation: {
                                speed: 2500
                            },
                            color: '#afcc12',
                            barWidth: 30,
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
                        yaxis: 'yaxis',
                        rendererOptions: {
                            // Speed up the animation a little bit.
                            // This is a number of milliseconds.  
                            // Default for bar series is 3000.  
                            animation: {
                                speed: 2000
                            },
                            color: '#fc7736',
                            barWidth: 30,
                            barPadding: -60,
                            barMargin: 0,
                            highlightMouseOver: true
                        }
                    }, {
                        pointLabels: {
                            show: false
                        },
                        renderer: $.jqplot.BarRenderer,
                        showHighlight: false,
                        yaxis: 'yaxis',
                        rendererOptions: {
                            // Speed up the animation a little bit.
                            // This is a number of milliseconds.  
                            // Default for bar series is 3000.  
                            animation: {
                                speed: 2200
                            },
                            color: '#eb2b2a',
                            barWidth: 30,
                            barPadding: -40,
                            barMargin: 0,
                            highlightMouseOver: true
                        }
                    }
                ],
                axesDefaults: {
                    pad: 0
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
                            tickInset: 0.5,
                            minorTicks: 1
                        }
                    },
                    yaxis: {
                        tickOptions: {
                            formatString: "%'d"
                        },
                        rendererOptions: {
                            forceTickAt0: true
                        }
                    },
                    y2axis: {
                        tickOptions: {
                            formatString: "%'d"
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
            $("#chart_height_this").bind('jqplotDataHighlight', function (ev, seriesIndex, pointIndex, data) {
            }
          );

            $("#chart_height_this").bind('jqplotDataClick', function (ev, seriesIndex, pointIndex, data) {
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
                $("#tooltip1b1").html('<span style="font-size:14px;font-weight:bold;color:' + color + ';">' + '나 : ' + data[1] + '<br>' + '학교 : ' + plot1.data.indexOf[seriesIndex] + '<br>' + '전체 : ' + data[1] + '<br>' + '</span>');
                $("#tooltip1b1").show();
            }
            );

            $("#chart_height_this").bind('jqplotDataUnhighlight', function (ev) {
                $("#info2").html("0");
            }
            );
        }