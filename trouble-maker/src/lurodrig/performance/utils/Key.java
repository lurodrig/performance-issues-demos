package lurodrig.performance.utils;

public class Key {
	Integer id;

	public Key(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
