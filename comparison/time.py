import matplotlib.pyplot as plt
import numpy as np

x_axis_data = [1,2,3,4,5,6,7]
y1_axis_data = [1,0,1,2,7,18,109]
y2_axis_data = [0,0,1,3,14,56,468]

plt.plot(x_axis_data, y1_axis_data, 'b*--', alpha=0.5, linewidth=1, label='Not tail recursion')
plt.plot(x_axis_data, y2_axis_data, 'ro--', alpha=0.5, linewidth=1, label='Tail recursion')

plt.legend()
plt.xlabel('Length(log)')
plt.ylabel('time(ms)')

plt.show()