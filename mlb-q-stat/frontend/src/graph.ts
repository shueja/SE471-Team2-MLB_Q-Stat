import { html, LitElement } from 'lit';
import '@vaadin/button';
import '@vaadin/text-field';
import * as d3 from 'd3';

type DataPoint = {date: number; value: number};
class HelloWorld extends LitElement {

    points: string = "";
static get properties() {
    return {
        points: { type: String }
    }
}
  render() {
    console.log(this.points)
    const data = JSON.parse(this.points) as [number, number][];
    console.log(data);
    // Declare the chart dimensions and margins.
    const width = 928 / 4;
    const height = 500 / 4;
    const marginTop = 40 / 2;
    const marginRight = 80 / 2;
    const marginBottom = 80 / 2;
    const marginLeft = 40 / 2;

    // // Declare the x (horizontal position) scale.
    // const x = d3.scaleLinear().domain(d3.extent(dataX)).range([marginLeft, width - marginRight]);

    const svg = d3.create("svg")
    .attr("width", (width *4 + marginLeft + marginRight))
    .attr("height", (height *4 + marginBottom + marginTop))
    .attr("viewBox", [-marginLeft, -marginTop, width + marginRight, height + marginBottom])
    .attr("style", "max-width: 100%; height: auto; height: intrinsic;");

    var x = d3.scaleLinear()
    //@ts-ignore
    .domain(d3.extent(data, function(d) { return d[0]; }))
    .range([ 0, width ]);

    // svg.append("g")
    // .attr("transform", "translate(0," + height + ")")
    // .call(d3.axisBottom(x));
    // // Declare the y (vertical position) scale.
    // const y = d3.scaleLinear([0, d3.max(aapl, d => d.value)], [height - marginBottom, marginTop]);
    var y = d3.scaleLinear()
    //@ts-ignore
    .domain([0, d3.max(data, function(d) { return d[1]; })])
    .range([ height, 0 ]);
    // svg.append("g")
    // .call(d3.axisLeft(y));
    // Declare the line generator.
    const line = d3.line().x(d=>x(d[0])).y(d=>y(d[1]));

    // Create the SVG container.

    const xAxisTicks = x.ticks()
        .filter(tick => Number.isInteger(tick));
    const xAxis = d3.axisBottom(x)
        .tickValues(xAxisTicks)
        .tickFormat(d3.format('d'));
    // Add the x-axis.
    svg.append("g")
        .attr("transform", `translate(0,${height})`)
        .call(xAxis);
    d3.selectAll(".xAxis>.tick>text")
        .each(function(d, i){
          d3.select(this).style("font-size","30px");
        });
    // Add the y-axis, remove the domain line, add grid lines and a label.
    svg.append("g")
        .attr("transform", `translate(0,0)`)
        .call(d3.axisLeft(y).ticks(height / 40))
        .call(g => g.select(".domain").remove())
        .call(g => g.selectAll(".tick line").clone()
            .attr("x2", width)
            .attr("stroke-opacity", 0.1))
        // .call(g => g.append("text")
        //     .attr("x", -marginLeft)
        //     .attr("y", 10)
        //     .attr("fill", "currentColor")
        //     .attr("text-anchor", "start")
        //     .text("↑ Daily close ($)"));

    // Append a path for the line.
    svg.append("path")
        .attr("fill", "none")
        .attr("stroke", "red")
        .attr("stroke-width", 1.5)
        .attr("d", line(data));

    return html`${svg.node()}`;

//     return html`
//       <div>
//         <vaadin-text-field id="firstInput"></vaadin-text-field>
//         <vaadin-button id="helloButton">Click me!</vaadin-button>
//       </div>
//     `;
  }
}

customElements.define('mlb-graph', HelloWorld);