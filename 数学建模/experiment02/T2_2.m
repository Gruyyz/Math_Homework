% 数据
t = 0:24;
T = [15, 14, 14, 14, 14, 15, 16, 18, 20, 22, 23, 25, 28,...
    31, 32, 31, 29, 27, 25, 24, 22, 20, 18, 17, 16];

% 二次函数拟合
p2 = polyfit(t, T, 2);
T2 = polyval(p2, t);
error2 = sum((T - T2).^2);

% 三次函数拟合
p3 = polyfit(t, T, 3);
T3 = polyval(p3, t);
error3 = sum((T - T3).^2);

% 四次函数拟合
p4 = polyfit(t, T, 4);
T4 = polyval(p4, t);
error4 = sum((T - T4).^2);

% 指数型函数拟合
f = @(b, t) b(1) * exp(-b(2) * (t - b(3)).^2);
b0 = [30, 0.1, 12]; % 初始猜测值
options = optimoptions('lsqcurvefit', 'Display', 'off');
b = lsqcurvefit(f, b0, t, T, [], [], options);
T_exp = f(b, t);
error_exp = sum((T - T_exp).^2);

% 绘图
figure;
hold on;
plot(t, T, 'ko', 'DisplayName', '原始数据');
plot(t, T2, 'r-', 'DisplayName', sprintf('二次函数 (误差平方和 = %.2f)', error2));
plot(t, T3, 'g-', 'DisplayName', sprintf('三次函数 (误差平方和 = %.2f)', error3));
plot(t, T4, 'b-', 'DisplayName', sprintf('四次函数 (误差平方和 = %.2f)', error4));
plot(t, T_exp, 'm-', 'DisplayName', sprintf('指数型函数 (误差平方和 = %.2f)', error_exp));
xlabel('时间 (小时)');
ylabel('温度 (\circC)');
title('气温变化记录及拟合曲线');
legend;
grid on;
hold off;

% 显示误差平方和
fprintf('二次函数误差平方和: %.2f\n', error2);
fprintf('三次函数误差平方和: %.2f\n', error3);
fprintf('四次函数误差平方和: %.2f\n', error4);
fprintf('指数型函数误差平方和: %.2f\n', error_exp);
