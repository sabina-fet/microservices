import './activityGraph.css';
import React from 'react';

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js'
import { Chart } from 'react-chartjs-2'
import { Line } from 'react-chartjs-2';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
)


function ActivityGraph() {
    return (
        <Line
          datasetIdKey='id'
          data={{
            labels: ['Jun', 'Jul', 'Aug', 'Sep', 'Oct'],
            datasets: [
              {
                data: [1, 5, 2, 4, 8],
              },
            ],
          }}
        />
    );
}

export default ActivityGraph;
