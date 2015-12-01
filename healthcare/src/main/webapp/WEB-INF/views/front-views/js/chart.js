var c1 = false;
var c2 = false;
var c3 = false;
var c4 = false;

function chart(ch, name) {
    var s1 = [[2, 162]]; //표준
    var s2 = [[3, 164]]; //반병균
    var s3 = [[4, 163]]; //학교평균

    var s4 = [[1, 155]]; //나
    var ticks = [[1, "나"], [2, "표준"], [3, "반평균"], [4, "학교평균"]];
    if (ch == false) { var c = true; } else { var c = false; }
    var chart_name = name;

    plot1 = $.jqplot(chart_name, [s4, s1, s2, s3], {
        // Turns on animatino for all series in this plot.

        animate: c,
        // Will animate plot on calls to plot1.replot({resetAxes:true})
        animateReplot: true,
        cursor: {
            show: true,
            zoom: true,
            looseZoom: true,
            showTooltip: false
        },
        series: [
            {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 1500
                    },
                    color: '#3fc6f3',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 2000
                    },
               //     color: '#afcc12',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 2500
                    },
               //     color: '#afcc12',
                    barWidth: 40,
                    barPadding: -30,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 3000
                    },
                 //   color: '#afcc12',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
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
                    formatString: "%'d",
                    showGridline: false,                    
                },
                drawMajorGridlines:false,
                rendererOptions: {
                    forceTickAt0: true
                }
            },
            y2axis: {
                tickOptions: {
                    formatString: "%'d",
                    showGridline: false
                },
                drawMajorGridlines:false,
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
}

function chart2(ch, name) {
    var s1 = [[2, 41.3]]; //표준
    var s2 = [[3, 45.7]]; //반병균
    var s3 = [[4, 46.2]]; //학교평균

    var s4 = [[1, 47]]; //나
    var ticks = [[1, "나"], [2, "표준"], [3, "반평균"], [4, "학교평균"]];
    if (ch == false) { var c = true; } else { var c = false; }
    var chart_name = name;

    plot1 = $.jqplot(chart_name, [s4, s1, s2, s3], {
        // Turns on animatino for all series in this plot.

        animate: c,
        // Will animate plot on calls to plot1.replot({resetAxes:true})
        animateReplot: true,
        cursor: {
            show: true,
            zoom: true,
            looseZoom: true,
            showTooltip: false
        },
        series: [
            {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',                
                shadow: false,               
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 1500
                    },
                    color: '#3fc6f3',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 2000
                    },
               //     color: '#afcc12',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 2500
                    },
               //     color: '#afcc12',
                    barWidth: 40,
                    barPadding: -30,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 3000
                    },
                 //   color: '#afcc12',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
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
                    formatString: "%'d",
                    showGridline: false,                    
                },
                drawMajorGridlines:false,
                rendererOptions: {
                    forceTickAt0: true
                }
            },
            y2axis: {
                tickOptions: {
                    formatString: "%'d",
                    showGridline: false
                },
                drawMajorGridlines:false,
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
}
var c11 = false; //신장 랭크
var c12 = false; //체중 랭크
var c13 = false; //bmi 랭크

function chart3(ch, name) {
       var s1 = [169,163,161,159,154,150,146,140,135]; //표준

    var ticks = [[1, "1반"], [2, "3반"], [3, "5반"], [4, "9반"],[5, "7반"], [6, "2반"], [7, "4반"], [8, "6반"]];
    if (ch == false) { var c = true; } else { var c = false; }
    var chart_name = name;

    plot1 = $.jqplot(chart_name, [s1], {
        // Turns on animatino for all series in this plot.

        animate: c,
        // Will animate plot on calls to plot1.replot({resetAxes:true})
        animateReplot: true,
        cursor: {
            show: true,
            zoom: true,
            looseZoom: true,
            showTooltip: false
        },
        series: [
            {
                pointLabels: {
                    show: false
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',                
                shadow: false,               
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 1500
                    },
                    color: '#eb2b2a',
                    barWidth: 30,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
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
                    tickInset: 0.5,
                    minorTicks: 1

                }
            },
            yaxis: {
                tickOptions: {
                    formatString: "%'d",
                    showGridline: false,                    
                },
                drawMajorGridlines:false,
                rendererOptions: {
                    forceTickAt0: true
                },
                min : 110,
                max : 180
            },
            y2axis: {
                tickOptions: {
                    formatString: "%'d",
                    showGridline: false
                },
                drawMajorGridlines:false,
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
}
function chart4(ch, name) {
    var s1 = [[2, 66]]; //표준
    var s2 = [[3, 63]]; //반병균
    var s3 = [[4, 60]]; //학교평균

    var s4 = [[1, 67]]; //나
    var ticks = [[1, "1반"], [2, "5반"], [3, "7반"], [4, "8반"]];
    if (ch == false) { var c = true; } else { var c = false; }
    var chart_name = name;

    plot1 = $.jqplot(chart_name, [s4, s1, s2, s3], {
        // Turns on animatino for all series in this plot.

        animate: c,
        // Will animate plot on calls to plot1.replot({resetAxes:true})
        animateReplot: true,
        cursor: {
            show: true,
            zoom: true,
            looseZoom: true,
            showTooltip: false
        },
        series: [
            {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',                
                shadow: false,               
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 1500
                    },
                    color: '#3fc6f3',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 2000
                    },
               //     color: '#afcc12',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 2500
                    },
               //     color: '#afcc12',
                    barWidth: 40,
                    barPadding: -30,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 3000
                    },
                 //   color: '#afcc12',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
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
                    formatString: "%'d",
                    showGridline: false,                    
                },
                drawMajorGridlines:false,
                rendererOptions: {
                    forceTickAt0: true
                }
            },
            y2axis: {
                tickOptions: {
                    formatString: "%'d",
                    showGridline: false
                },
                drawMajorGridlines:false,
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
}
function chart5(ch, name) {
    var s1 = [[2, 25.2]]; //표준
    var s2 = [[3, 23.1]]; //반병균
    var s3 = [[4, 18.5]]; //학교평균

    var s4 = [[1, 27.1]]; //나
    var ticks = [[1, "7반"], [2, "1반"], [3, "9반"], [4, "2반"]];
    if (ch == false) { var c = true; } else { var c = false; }
    var chart_name = name;

    plot1 = $.jqplot(chart_name, [s4, s1, s2, s3], {
        // Turns on animatino for all series in this plot.

        animate: c,
        // Will animate plot on calls to plot1.replot({resetAxes:true})
        animateReplot: true,
        cursor: {
            show: true,
            zoom: true,
            looseZoom: true,
            showTooltip: false
        },
        series: [
            {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',                
                shadow: false,               
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 1500
                    },
                    color: '#3fc6f3',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 2000
                    },
               //     color: '#afcc12',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 2500
                    },
               //     color: '#afcc12',
                    barWidth: 40,
                    barPadding: -30,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 3000
                    },
                 //   color: '#afcc12',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
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
                    formatString: "%'d",
                    showGridline: false,                    
                },
                drawMajorGridlines:false,
                rendererOptions: {
                    forceTickAt0: true
                }
            },
            y2axis: {
                tickOptions: {
                    formatString: "%'d",
                    showGridline: false
                },
                drawMajorGridlines:false,
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
}
var c5 = false;
var c6 = false;
var c7 = false;

var c8 = false;
var c9 = false;
var c10 = false;

function chart_detail(ch, name) {
    var s1 = [[2, 162]]; //표준
    var s2 = [[3, 164]]; //반병균
    var s3 = [[4, 163]]; //학교평균

    var s4 = [[1, 155]]; //나
    var ticks = [[1, "9월"], [2, "10월"], [3, "11월"], [4, "12월"]];
    if (ch == false) { var c = true; } else { var c = false; }
    var chart_name = name;

    plot1 = $.jqplot(chart_name, [s4, s1, s2, s3], {
        // Turns on animatino for all series in this plot.

        animate: c,
        // Will animate plot on calls to plot1.replot({resetAxes:true})
        animateReplot: true,
        cursor: {
            show: true,
            zoom: true,
            looseZoom: true,
            showTooltip: false
        },
        series: [
            {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 1500
                    },
                    color: '#3fc6f3',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 2000
                    },
                    color: '#afcc12',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 2500
                    },
                    color: '#afcc12',
                    barWidth: 40,
                    barPadding: -30,
                    barMargin: 0,
                    highlightMouseOver: false
                }
            }, {
                pointLabels: {
                    show: true
                },
                renderer: $.jqplot.BarRenderer,
                showHighlight: false,
                yaxis: 'yaxis',
                rendererOptions: {
                    // Speed up the animation a little bit.
                    // This is a number of milliseconds.  
                    // Default for bar series is 3000.  
                    animation: {
                        speed: 3000
                    },
                    color: '#afcc12',
                    barWidth: 40,
                    barPadding: -40,
                    barMargin: 0,
                    highlightMouseOver: false
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
}