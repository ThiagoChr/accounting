package br.accounting.domain.util.keygenerator;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

public final class UUIDUtil {

	private static TimeBasedGenerator uuidGenerator;

	static {
		EthernetAddress nic = EthernetAddress.fromInterface();
		uuidGenerator = Generators.timeBasedGenerator(nic);
	}

	public static String gerarUID() {
		synchronized (uuidGenerator) {
			return uuidGenerator.generate().toString();
		}
	}

	public static Long gerarLongUID() {
		synchronized (uuidGenerator) {
			return uuidGenerator.generate().timestamp();
		}
	}
}
