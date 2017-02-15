import { PropTypes } from 'react';
import { requireNativeComponent } from 'react-native';

var iface = {
    name: 'ImageView',
    propTypes: {
        src: PropTypes.string,
        borderRadius: PropTypes.number,
        resizeMode: PropTypes.oneOf(['cover', 'contain', 'stretch']),
    },
};

module.exports = requireNativeComponent('RCtImageView', iface);
