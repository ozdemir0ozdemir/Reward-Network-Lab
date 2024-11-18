package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rewards.RewardNetwork;
import rewards.internal.RewardNetworkImpl;
import rewards.internal.account.AccountRepository;
import rewards.internal.account.JdbcAccountRepository;
import rewards.internal.restaurant.JdbcRestaurantRepository;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.JdbcRewardRepository;
import rewards.internal.reward.RewardRepository;

import javax.sql.DataSource;

/**
 * - Creating Spring configuration class
 * - Defining bean definitions within the configuration class
 * - Specifying the dependency relationships among beans
 * - Injecting dependencies through constructor injection
 * - Creating Spring application context in the test code
 *   (WITHOUT using Spring testContext framework)
 *
 * - Use an appropriate annotation.
 *
 * - The names of the beans should be:
 *   - rewardNetwork
 *   - accountRepository
 *   - restaurantRepository
 *   - rewardRepository
 *
 * - Each repository implementation has a DataSource
 *   property to be set, but the DataSource is defined
 *   elsewhere (TestInfrastructureConfig.java), so you
 *   will need to define a constructor for this class
 *   that accepts a DataSource parameter.
 * - As it is the only constructor, @Autowired is optional.
 *
 * - You can create beans from the following implementation classes
 *   - rewardNetwork bean from RewardNetworkImpl class
 *   - accountRepository bean from JdbcAccountRepository class
 *   - restaurantRepository bean from JdbcRestaurantRepository class
 *   - rewardRepository bean from JdbcRewardRepository class
 * - Note that return type of each bean method should be an interface
 *   not an implementation.
 */

@Configuration
public class RewardsConfig {

	// Set this by adding a constructor.
	private final DataSource dataSource;

	public RewardsConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	protected RewardNetwork rewardNetwork(AccountRepository accountRepository,
										  RestaurantRepository restaurantRepository,
										  RewardRepository rewardRepository) {
		return new RewardNetworkImpl(accountRepository, restaurantRepository, rewardRepository);
	}

	@Bean
	protected AccountRepository accountRepository() {
		JdbcAccountRepository repository = new JdbcAccountRepository();
		repository.setDataSource(this.dataSource);
		return repository;
	}

	@Bean
	protected RestaurantRepository restaurantRepository() {
		JdbcRestaurantRepository repository = new JdbcRestaurantRepository();
		repository.setDataSource(this.dataSource);
		return repository;
	}

	@Bean
	protected RewardRepository rewardRepository() {
		JdbcRewardRepository repository = new JdbcRewardRepository();
		repository.setDataSource(this.dataSource);
		return repository;
	}
}
