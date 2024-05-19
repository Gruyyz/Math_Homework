clear;
x = [0.1,0.8,1.3,1.9,2.5,3.1];
y = [1.2,1.6,2.7,2.0,1.3,0.5];
x0 = 2.0;
y0_linear = interp1(x, y, x0, 'linear');
y0_spline = interp1(x, y, x0, 'spline');
disp(y0_linear)
disp(y0_spline)