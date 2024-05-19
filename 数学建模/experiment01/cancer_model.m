% 定义微分方程模型
function dNdt = cancer_model(t, N, k, r)
    dNdt = k * N - r * N;
end
