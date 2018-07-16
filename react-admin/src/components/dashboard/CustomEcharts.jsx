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

const generateOption = (title,data=[]) => {
    return {
        title: {
            text: title,
            left: '50%',
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
            data: data.map(r=>r.resultValue),
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
            name: '昨日',
            type: 'line',
            smooth: true,
            showSymbol: false,
            symbol: 'circle',
            symbolSize: 6,
            data: data.map(r=>r.count),
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
    };
}

export const EchartsViews = (title,data) => {
    return (
        <ReactEcharts
            option={generateOption(title,data)}
            style={{height: '350px', width: '100%'}}
            className={'react_for_echarts'}
        />
    );
}

export const CustomEcharts = (title,data) => {
    return (
        <ReactEcharts
            option={generateOption(title,data)}
            style={{height: '212px', width: '100%'}}
            className={'react_for_echarts'}
        />
    );
};