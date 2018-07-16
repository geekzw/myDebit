/**
 * Created by hao.cheng on 2017/5/5.
 */
import React from 'react';
import ReactEcharts from 'echarts-for-react';
import echarts from 'echarts';

// let xAxisData = [];
// let data = [];
// for (let i = 0; i < 50; i++) {
//     xAxisData.push(i);
//     data.push(Math.ceil((Math.cos(i / 5) * (i / 5) + i / 6) * 5) + 10);
// }

const style1 = (title,xData,yData) => ({
    title: {
        text: '',
        left: 'center',
        textStyle: {
            color: '#ccc',
            fontSize: 10
        }
    },
    backgroundColor: '#08263a',
    xAxis: [{
        show: true,
        data: xData.map(r=>(r.dayNumber||r.resultValue)),
        axisLabel: {
            textStyle: {
                color: '#ccc'
            }
        }
    }, {
        show: false,
        data: xData.map(r=>r.resultValue)
    }],
    tooltip: {},
    visualMap: {
        show: false,
        min: 0,
        max: 50,
        dimension: 0,
        inRange: {
            color: ['#4a657a', '#308e92', '#b1cfa5', '#f5d69f', '#f5898b', '#ef5055']
        }
    },
    yAxis: {
        axisLine: {
            show: false
        },
        axisLabel: {
            textStyle: {
                color: '#ccc'
            }
        },
        splitLine: {
            show: true,
            lineStyle: {
                color: '#08263f'
            }
        },
        axisTick: {
            show: false
        }
    },
    series: [
        {
        name: 'Simulate Shadow',
        type: 'line',
        data: yData.map(r=>r.count),
        z: 2,
        showSymbol: false,
        animationDelay: 0,
        animationEasing: 'linear',
        animationDuration: 1200,
        lineStyle: {
            normal: {
                color: 'transparent'
            }
        },
        areaStyle: {
            normal: {
                color: '#08263a',
                shadowBlur: 50,
                shadowColor: '#000'
            }
        }
    }, {
        name: yData.barTitle,
        type: 'bar',
        data: yData.map(r=>r.count),
        xAxisIndex: 1,
        z: 3,
        itemStyle: {
            normal: {
                barBorderRadius: 5
            }
        }
    }],
    animationEasing: 'elasticOut',
    animationEasingUpdate: 'elasticOut',
    animationDelay: function (idx) {
        return idx * 20;
    },
    animationDelayUpdate: function (idx) {
        return idx * 20;
    }
});

const style2 = (title,xData,yData) => ({
    title: {
        text: title,
        left: 'center',
        show: false,
        textAlign: 'center'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            lineStyle: {
                color: '#ddd'
            }
        },
        backgroundColor: 'rgba(255,255,255,1)',
        padding: [5, 10],
        textStyle: {
            color: '#7588E4',
        },
        extraCssText: 'box-shadow: 0 0 5px rgba(0,0,0,0.3)'
    },
    legend: {
        right: 20,
        orient: 'vertical',
    },
    xAxis: {
        type: 'category',
        data: xData.map(r=>(r.dayNumber||r.resultValue)),
        boundaryGap: false,
        splitLine: {
            show: true,
            interval: 'auto',
            lineStyle: {
                color: ['#D4DFF5']
            }
        },
        axisTick: {
            show: false
        },
        axisLine: {
            lineStyle: {
                color: '#609ee9'
            }
        },
        axisLabel: {
            margin: 10,
            textStyle: {
                fontSize: 10
            }
        }
    },
    yAxis: {
        type: 'value',
        splitLine: {
            lineStyle: {
                color: ['#D4DFF5']
            }
        },
        axisTick: {
            show: false
        },
        axisLine: {
            lineStyle: {
                color: '#609ee9'
            }
        },
        axisLabel: {
            margin: 0,
            textStyle: {
                fontSize: 8
            }
        }
    },
    series: [{
        name: title,
        type: 'line',
        smooth: true,
        showSymbol: false,
        symbol: 'circle',
        symbolSize: 6,
        data: yData.map(r=>r.count),
        areaStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(216, 244, 247,1)'
                }, {
                    offset: 1,
                    color: 'rgba(216, 244, 247,1)'
                }], false)
            }
        },
        itemStyle: {
            normal: {
                color: '#58c8da'
            }
        },
        lineStyle: {
            normal: {
                width: 3
            }
        }
    }]
});

const generateOption = (title,data=[],style=1) => {
    return (style===1 ?
        style1(title,data,data) :
        style2(title,data,data));
}

export const EchartsViews = (title,data,style) => {
    return (
        <ReactEcharts
            option={generateOption(title,data,style)}
            style={{height: '350px', width: '100%'}}
            className={'react_for_echarts'}
        />
    );
}