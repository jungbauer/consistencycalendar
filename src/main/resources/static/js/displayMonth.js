function drawMonth(selector, monthDate, checkins) {
    var minDate = new Date(monthDate);
    var maxDate = new Date(monthDate);

    var calendarRows = function(month) {
        var m = d3.timeMonth.floor(month);
        return d3.timeWeeks(d3.timeWeek.floor(m), d3.timeMonth.offset(m,1)).length;
    };

    var cellMargin = 2,
        cellSize = 20;

    // https://github.com/d3/d3-time-format
    var day = d3.timeFormat("%u"), // %u - Monday-based (ISO 8601) weekday as a decimal number [1,7].
        week = d3.timeFormat("%W"), // %W - Monday-based week of the year as a decimal number [00,53].
        format = d3.timeFormat("%Y-%m-%d"),
        titleFormat = d3.utcFormat("%a, %d-%b"),
        monthName = d3.timeFormat("%B"),
        months= d3.timeMonth.range(d3.timeMonth.floor(minDate), maxDate);

    // each month gets an SVG. svgs are drawn left to right
    var svg = d3.select(selector).selectAll("svg")
        .data(months)
        .enter().append("svg")
        .attr("class", "month")
        .attr("width", (cellSize * 7) + (cellMargin * 8) )
        .attr("height", function(d) {
            var rows = calendarRows(d);
            return (cellSize * rows) + (cellMargin * (rows + 1)) + 20; // the 20 is for the month labels
        })
        .append("g");

    // Text labels for the months
    svg.append("text")
        .attr("class", "month-name")
        .attr("x", ((cellSize * 7) + (cellMargin * 8)) / 2 )
        .attr("y", 15)
        .attr("text-anchor", "middle")
        .text(function(d) { return monthName(d); });

    // Draws a rounded square for each day in the months.
    var rect = svg.selectAll("rect.day")
        .data(function(d, i) {
            return d3.timeDays(d, new Date(d.getFullYear(), d.getMonth()+1, 1));
        })
        .enter().append("rect")
        .attr("class", "day")
        .attr("width", cellSize)
        .attr("height", cellSize)
        .attr("rx", 3).attr("ry", 3) // rounded corners
        .attr("fill", '#eaeaea') // default light grey fill
        .attr("x", function(d) {
            return ((day(d)-1) * cellSize) + ((day(d)-1) * cellMargin) + cellMargin; // shift day coz they use 1-7 for monday week
        })
        .attr("y", function(d) {
            return ((week(d) - week(new Date(d.getFullYear(),d.getMonth(),1))) * cellSize) +
                ((week(d) - week(new Date(d.getFullYear(),d.getMonth(),1))) * cellMargin) +
                cellMargin + 20;
        })
        .on("mouseover", function(d) {
            d3.select(this).classed('hover', true);
        })
        .on("mouseout", function(d) {
            d3.select(this).classed('hover', false);
        })
        .datum(format);

    // creates a title to show when hovering over a day.
    rect.append("title")
        .text(function(d) { return titleFormat(new Date(d)); });

    var lookup = d3.nest()
        .key(function(d) { return d; })
        .rollup(function(leaves) { return leaves.length; })
        .object(checkins);

    // fills in a colour for a rect if there are associated counts.
    rect.filter(function(d) { return d in lookup; })
        .style("fill", '#111111')
        .classed("clickable", true)
        .select("title")
        .text(function(d) { return titleFormat(new Date(d)) + ":  " + lookup[d]; });
}