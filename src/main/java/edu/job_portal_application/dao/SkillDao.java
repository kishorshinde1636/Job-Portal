package edu.job_portal_application.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.job_portal_application.entity.Skill;
import edu.job_portal_application.repo.SkillRepo;

@Repository
public class SkillDao {

	@Autowired
	private SkillRepo skillRepo;

	public Skill addSkill(Skill skill) {
		return skillRepo.save(skill);
	}

	public Skill getSkillByName(String skillName) {
		Optional<Skill> optional = skillRepo.getSkillByName(skillName);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
}
