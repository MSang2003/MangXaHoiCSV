function deleteUser(url) {
    fetch(url, {
        method: 'delete',
        headers: {
            'Authorization': 'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6IkFkbWluU2FuZyIsImV4cCI6MTcxODc4NTYwMH0.taZUAYIACknJ4p9JO0WgczrZTETpr2Fm6Sci9659_ZI'
        }
    }).then(res => {
        if (res.status === 204) {
            location.reload();
        } else {
            alert("ERROR");
        }
    }).catch(err => {
        alert("Request failed: " + err);
    });
}


function deletePost(url, id) {
    fetch(url, {
        method: 'delete',
        headers: {
            'Authorization': 'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6IkFkbWluU2FuZyIsImV4cCI6MTcxODc4NTYwMH0.taZUAYIACknJ4p9JO0WgczrZTETpr2Fm6Sci9659_ZI'
        }
    }).then(res => {
        console.log(res.status )
        if (res.status === 204)
            location.reload();
        else
            alert("ERROR");
    });
}


function drawChartRevenue(ctx, labels, data) {
    let colors = [];
    for (let i = 0; i < data.length; i++)
        colors.push(`rgba(${parseInt(Math.random()*255)}, 
        ${parseInt(Math.random()*255)}, 
        ${parseInt(Math.random()*255)}, 0.7)`);
    
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                    label:"",
                    data: data,
                    borderWidth: 1,
                    backgroundColor: colors
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }, plugins: {
            legend: {
                display: false
            }
        }
        }
    });
}