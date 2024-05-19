    clear;
    x = [1.2,1.8,2.1,2.4,2.6,3.0,3.3];
    y = [4.85,5.2,5.6,6.2,6.5,7.0,7.5];
    p4 = polyfit(x, y, 4);
    p5 = polyfit(x, y, 5);
    p6 = polyfit(x, y, 6);
    x_fit = 1.2:0.01:3.3;
    y_fit4 = polyval(p4, x_fit);
    y_fit5 = polyval(p5, x_fit);
    y_fit6 = polyval(p6, x_fit);
    plot(x, y, 'o', x_fit, y_fit4, x_fit, y_fit5, x_fit, y_fit6);
    xlabel('x')
    ylabel('y')
    legend('数据点', '4阶多项式拟合曲线', '5阶多项式拟合曲线', '6阶多项式拟合曲线');