package com.pkweb.backend1.Services;

import com.pkweb.backend1.Entity.Problem;
import com.pkweb.backend1.Repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    /**
     * 获取所有的问题
     * @return 所有问题的列表
     */
    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    /**
     * 获取随机问题
     * @return 一个随机选中的问题
     */
    public Problem getRandomProblem() {
        List<Problem> problems = getAllProblems();

        if (problems.isEmpty()) {
            return null;
        }

        Random random = new Random();
        return problems.get(random.nextInt(problems.size()));
    }

    // 其他服务方法...
}
